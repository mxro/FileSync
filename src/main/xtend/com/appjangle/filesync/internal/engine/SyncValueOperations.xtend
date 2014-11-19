package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.Date

class SyncValueOperations {
	
	
	
	def downloadValue(Node node, Metadata metadata, FileItem folder) {
		
		if (!folder.get("value.txt").exists) {
			folder.createFile("value.txt").text = node.value().toString()
			
			metadata.value = new ItemMetadata() {
				
				override name() {
					"value.txt"
				}
				
				override lastModified() {
					folder.get("value.txt").lastModified
				}
				
				override uri() {
					node.uri()
				}
				
				override hash() {
					folder.get("value.txt").hash
				}
				
				override converter() {
					""
				}
				
			}
			
			return;
		}
		
		
		
	}
	
}