package com.appjangle.filesync.engine

import com.appjangle.filesync.engine.metadata.FileItemMetaData
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList
import java.util.List

class FolderSynchronization {
	
	def nodeToFolder(Node node, FileItem folder, ValueCallback<NodeToFolderSynchronizationResult> cb) {
		
		if (!folder.directory)
		  throw new Exception('File passed and not directory. '+folder)
		  
		
		if (!folder.exists)
		  throw new Exception('File passed does not exist. '+folder)
		  
		
		val metadata = folder.assertFolder(".filesync-meta")
		metadata.visible = false;
		
		val nodes = MetadataUtilsJre.readFromFile(metadata.getChild("nodes.xml"))
		
		val locallyChangedFiles = determineLocallyChangedFiles(nodes, folder)
		
	}
	
	def determineLocallyChangedFiles(NodesMetadata metadata, FileItem folder) {
		
		
		val cachedChildren = metadata.getChildren
		
		for (FileItem child: folder.getChildren) {
			if
		}
		
		
	}
	
	
	def getNames(List<FileItemMetaData> cachedChildren) {
		
		
		val res = new ArrayList<String>(cachedChildren.size)
		
		
		res
		
	}
	
}