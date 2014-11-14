package com.appjangle.filesync.engine

import de.mxro.file.FileItem
import io.nextweb.Node

class SyncFolder {
	
	extension FileUtils fileUtils = new FileUtils()
	
	def doIt(FileItem folder, Node node) {
		
		if (folder.hasMetadata) {
			
		}
		
		val metadata = folder.assertMetadata
		
	}
	
}