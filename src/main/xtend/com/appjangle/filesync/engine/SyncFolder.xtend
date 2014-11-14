package com.appjangle.filesync.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.file.FileItem
import io.nextweb.Node

class SyncFolder {
	
	val FileItem folder
	val Node node
	val Converter converter
	
	new(FileItem folder, Node node) {
		this.folder = folder
		this.node = node
		this.converter = converter
	}
	
	var Metadata metadata 

	def doIt() {
		
		if (!folder.hasMetadata) {
			metadata = folder.assertMetadata
			fullDownload
			return;
		}
		
		metadata = folder.assertMetadata
		
		new FileToNetworkOperations(node, folder, metadata)
		
	}
	
	def fullDownload() {
		
	}
	
	extension FileUtils fileUtils = new FileUtils()
	
}