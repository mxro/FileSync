package com.appjangle.filesync.engine

import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import com.appjangle.filesync.engine.metadata.NodesMetadata

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
		
		
		
	}
	
}