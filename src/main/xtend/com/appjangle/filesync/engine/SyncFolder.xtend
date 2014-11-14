package com.appjangle.filesync.engine

import de.mxro.file.FileItem
import io.nextweb.Node

class SyncFolder {
	
	val FileItem folder
	val Node node
	
	new(FileItem folder, Node node) {
		this.folder = folder
		this.node = node
	}
	
	
	
	def doIt() {
		
		if (!folder.hasMetadata) {
			fullDownload
			return;
		}
		
		val metadata = folder.assertMetadata
		
	}
	
	def fullDownload() {
		
	}
	
	extension FileUtils fileUtils = new FileUtils()
	
}