package com.appjangle.filesync.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.collections.CollectionsUtils
import io.nextweb.Node
import java.util.ArrayList
import java.util.List
import com.appjangle.filesync.Converter
import com.appjangle.filesync.engine.metadata.Metadata
import com.appjangle.filesync.engine.metadata.ItemMetadata

class FileToNetworkOperations {

	val Node node;
	val FileItem folder;
	val Metadata metadata;
	val Converter converter;	
	
	
	new(Node node, FileItem folder, Metadata metadata, Converter converter) {
		this.node = node
		this.folder = folder
		this.metadata = metadata
		this.converter = converter
		
	}
	


	def fileToNetworkOperations( ValueCallback<List<NetworkOperation>> cb) {

		if (!folder.directory)
			throw new Exception('File passed and not directory. ' + folder)

		if (!folder.exists)
			throw new Exception('File passed does not exist. ' + folder)

		
		val locallyAddedFiles = determineLocallyAddedFiles(metadata, folder)

		val locallyRemovedFiles = determineLocallyRemovedFiles(metadata, folder)

		val locallyChangedFiles = determineLocallyChangedFiles(metadata, folder)

		
		val agg = Async.collect(3, Async.embed(cb, [res |
			val ops = CollectionsUtils.flatten(res)
			
			cb.onSuccess(ops)
			
		]));
		
		createOperationsFromRemovedFiles(locallyRemovedFiles, agg.createCallback)
		createOperationsFromChangedFiles(locallyChangedFiles, agg.createCallback)		
		createOperationsFromCreatedFiles(locallyAddedFiles, agg.createCallback)
		
	}

   

	def createOperationsFromChangedFiles(List<String> fileNames, ValueCallback<List<NetworkOperation>> cb) {


		val agg = Async.collect(fileNames.size, Async.embed(cb, [ res |
			cb.onSuccess(CollectionsUtils.flatten(res))
		]))

		fileNames.forEach[ fileName | 
			converter.update(metadata, folder.getChild(fileName),  agg.createCallback());
		]
		
	}

	def createOperationsFromRemovedFiles(List<String> fileNames, ValueCallback<List<NetworkOperation>> cb) {


		val agg = Async.collect(fileNames.size, Async.embed(cb, [ res |
			cb.onSuccess(CollectionsUtils.flatten(res))
		]))

		fileNames.forEach[ fileName | 
			converter.deleteNodes(metadata, metadata.get(fileName),  agg.createCallback)
		]
		
	}


	def createOperationsFromCreatedFiles(List<String> fileNames, ValueCallback<List<NetworkOperation>> cb) {
		
		val agg = Async.collect(fileNames.size, Async.embed(cb, [ res |
			cb.onSuccess(CollectionsUtils.flatten(res))
		]))
		
		fileNames.forEach[fileName |
			converter.createNodes(metadata, folder.getChild(fileName), agg.createCallback());
		]
		
	}

	static def determineLocallyChangedFiles(Metadata metadata, FileItem folder) {

		val res = new ArrayList<String>(0)

		for (ItemMetadata fileMetadata : metadata.children) {

			val item = folder.getChild(fileMetadata.name)

			if (item.exists) {
				if (item.lastModified.time > fileMetadata.lastModified.time) {
					res.add(item.name)
				}
			}

		}

		res

	}

	static def determineLocallyAddedFiles(Metadata metadata, FileItem folder) {

		val previousNames = getNamesFromCache(metadata.children)

		val currentNames = getNames(folder.children)

		currentNames.removeAll(previousNames)
		return currentNames

	}

	static def determineLocallyRemovedFiles(Metadata metadata, FileItem folder) {

		val previousNames = getNamesFromCache(metadata.children)

		val currentNames = getNames(folder.children)

		previousNames.removeAll(currentNames)
		return previousNames

	}

	static def getNamesFromCache(List<ItemMetadata> cachedChildren) {

		val res = new ArrayList<String>(cachedChildren.size)

		for (ItemMetadata fileItemMetaData : cachedChildren) {
			res.add(fileItemMetaData.name)
		}

		return res

	}

	static def getNames(List<FileItem> cachedChildren) {

		val res = new ArrayList<String>(cachedChildren.size)

		for (FileItem fileItem : cachedChildren) {
			res.add(fileItem.name)
		}

		return res

	}

	
	

}
