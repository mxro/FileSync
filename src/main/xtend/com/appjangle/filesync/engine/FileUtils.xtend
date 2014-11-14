package com.appjangle.filesync.engine

import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import de.mxro.file.FileItem

class FileUtils {
	
	def hasMetadata(FileItem forFolder) {

		forFolder.assertFolder(".filesync-meta").getChild("nodes.xml").exists
	}
	
	def assertMetadata(FileItem forFolder) {
		val metadataFolder = forFolder.assertFolder(".filesync-meta")
		
		metadataFolder.visible = false;
		
		if (!metadataFolder.getChild("nodes.xml").exists) {
			metadataFolder.createFile("nodes.xml")
		}
		
		MetadataUtilsJre.readFromFile(metadataFolder.getChild("nodes.xml"))
	}
	
}