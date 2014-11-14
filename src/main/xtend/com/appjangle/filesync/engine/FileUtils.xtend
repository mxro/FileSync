package com.appjangle.filesync.engine

import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.FileOperationContext
import com.appjangle.filesync.engine.metadata.Metadata
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import de.mxro.file.FileItem
import java.util.List

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
	
	def execute(List<FileOperation> operations, FileItem folder, Metadata metadata) {
		
		val ctx = new FileOperationContext() {
			
			override folder() {
				folder
			}
			
			override metadata() {
				metadata
			}
			
		}
		
		for (op : operations) {
			op.apply(ctx)
			
		}
		
	}
	
}