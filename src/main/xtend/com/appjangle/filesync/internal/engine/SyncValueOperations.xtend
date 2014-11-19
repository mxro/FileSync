package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import de.mxro.file.FileItem
import io.nextweb.Node

class SyncValueOperations {
	
	def static createMetadata(Node node, FileItem forFile) {
		new ItemMetadata() {
				
				override name() {
					forFile.name
				}
				
				override lastModified() {
					forFile.lastModified
				}
				
				override uri() {
					node.uri()
				}
				
				override hash() {
					forFile.hash
				}
				
				override converter() {
					""
				}
				
			}
	}
	
	def downloadValue(Node node, Metadata metadata, FileItem folder) {
		
		if (!folder.get("value.txt").exists) {
			folder.createFile("value.txt").text = node.value().toString()
			
			metadata.value = createMetadata(node, folder.get("value.txt"))
			
			return;
		}
		
		val oldText = folder.get("value.txt").text
		
		if (oldText != node.value().toString()) {
			folder.get("value.txt").text = node.value().toString()
		}
		
	}
	
}