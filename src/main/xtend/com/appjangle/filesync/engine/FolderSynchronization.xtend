package com.appjangle.filesync.engine

import com.appjangle.filesync.Convert
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.FileItemMetaData
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList
import java.util.List

class FolderSynchronization {

	val Convert convert = null;

	def getLocalOperations(Node node, FileItem folder, ValueCallback<NodeToFolderSynchronizationResult> cb) {

		if (!folder.directory)
			throw new Exception('File passed and not directory. ' + folder)

		if (!folder.exists)
			throw new Exception('File passed does not exist. ' + folder)

		val metadata = folder.assertFolder(".filesync-meta")
		metadata.visible = false;

		val nodes = MetadataUtilsJre.readFromFile(metadata.getChild("nodes.xml"))

		if (nodes == null) {
			return new ArrayList<NetworkOperation>(0)
		}

		val locallyAddedFiles = determineLocallyAddedFiles(nodes, folder)

		val locallyRemovedFiles = determineLocallyRemovedFiles(nodes, folder)

		val locallyChangedFiles = determineLocallyChangedFiles(nodes, folder)

	}

	def createOperationsFromChangedFiles(List<String> fileNames, Node node, int idx, FileItem folder, List<NetworkOperation> res, ValueCallback<List<NetworkOperation>> cb) {

		if (idx >= fileNames.size) {
			cb.onSuccess(res)
			return
		}
			
			
		convert.update(folder.getChild(fileNames.get(idx)), node, [ ops | ]);
	
	
	}

	static def determineLocallyChangedFiles(NodesMetadata metadata, FileItem folder) {

		val res = new ArrayList<String>(0)

		for (FileItemMetaData fileMetadata : metadata.children) {

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

	static def getNamesFromCache(List<FileItemMetaData> cachedChildren) {

		val res = new ArrayList<String>(cachedChildren.size)

		for (FileItemMetaData fileItemMetaData : cachedChildren) {
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
