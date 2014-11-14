package com.appjangle.filesync.engine

import com.appjangle.filesync.Convert
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.collections.CollectionsUtils
import io.nextweb.Node
import java.util.ArrayList
import java.util.List
import com.appjangle.filesync.engine.metadata.FileItemMetadata

class GetLocalOperationsProcess {

	val Convert convert = null;

	val Node node = null;
	val FileItem folder = null;
	
	var NodesMetadata nodes = null;

	def getLocalOperations( ValueCallback<List<NetworkOperation>> cb) {

		if (!folder.directory)
			throw new Exception('File passed and not directory. ' + folder)

		if (!folder.exists)
			throw new Exception('File passed does not exist. ' + folder)

		val metadata = folder.assertFolder(".filesync-meta")
		
		metadata.visible = false;

		nodes = MetadataUtilsJre.readFromFile(metadata.getChild("nodes.xml"))

		if (nodes == null) {
			return new ArrayList<NetworkOperation>(0)
		}

		val locallyAddedFiles = determineLocallyAddedFiles(nodes, folder)

		val locallyRemovedFiles = determineLocallyRemovedFiles(nodes, folder)

		val locallyChangedFiles = determineLocallyChangedFiles(nodes, folder)

		
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
			convert.update(nodes, folder.getChild(fileName),  agg.createCallback());
		]
		
	}

	def createOperationsFromRemovedFiles(List<String> fileNames, ValueCallback<List<NetworkOperation>> cb) {


		val agg = Async.collect(fileNames.size, Async.embed(cb, [ res |
			cb.onSuccess(CollectionsUtils.flatten(res))
		]))

		fileNames.forEach[ fileName | 
			convert.deleteNodes(nodes, nodes.getChild(fileName),  agg.createCallback)
		]
		
	}


	def createOperationsFromCreatedFiles(List<String> fileNames, ValueCallback<List<NetworkOperation>> cb) {
		
		val agg = Async.collect(fileNames.size, Async.embed(cb, [ res |
			cb.onSuccess(CollectionsUtils.flatten(res))
		]))
		
		fileNames.forEach[fileName |
			convert.createNodes(nodes, folder.getChild(fileName), agg.createCallback());
		]
		
	}

	static def determineLocallyChangedFiles(NodesMetadata metadata, FileItem folder) {

		val res = new ArrayList<String>(0)

		for (FileItemMetadata fileMetadata : metadata.children) {

			val item = folder.getChild(fileMetadata.name)

			if (item.exists) {
				if (item.lastModified.time > fileMetadata.lastModified.time) {
					res.add(item.name)
				}
			}

		}

		res

	}

	static def determineLocallyAddedFiles(NodesMetadata metadata, FileItem folder) {

		val previousNames = getNamesFromCache(metadata.children)

		val currentNames = getNames(folder.children)

		currentNames.removeAll(previousNames)
		return currentNames

	}

	static def determineLocallyRemovedFiles(NodesMetadata metadata, FileItem folder) {

		val previousNames = getNamesFromCache(metadata.children)

		val currentNames = getNames(folder.children)

		previousNames.removeAll(currentNames)
		return previousNames

	}

	static def getNamesFromCache(List<FileItemMetadata> cachedChildren) {

		val res = new ArrayList<String>(cachedChildren.size)

		for (FileItemMetadata fileItemMetaData : cachedChildren) {
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
