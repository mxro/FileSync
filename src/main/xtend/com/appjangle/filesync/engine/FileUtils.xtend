package com.appjangle.filesync.engine

import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.file.FileItem

class FileUtils {
	
	
	def Metadata assertMetadata(FileItem forFolder) {
		val metadataFolder = forFolder.assertFolder(".filesync-meta")
		
		metadataFolder.visible = false;
		
		if (!metadataFolder.getChild("nodes.xml").exists) {
			metadataFolder.createFile("nodes.xml")
		}
		
		val metadata = MetadataUtilsJre.readFromFile(metadataFolder.getChild("nodes.xml"))
	}
	
}