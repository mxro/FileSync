package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Metadata
import de.mxro.file.FileItem
import io.nextweb.Node
import com.appjangle.filesync.internal.engine.metadata.MetadataUtilsJre
import com.appjangle.filesync.ItemMetadata

class SyncValueOperations {
	
	def downloadValue(Node node, Metadata metadata, FileItem folder) {
		
		if (!folder.get("value.txt").exists) {
			folder.createFile("value.txt").text = node.value().toString()
			
			metadata.value = new ItemMetadata() {
				
				override name() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override lastModified() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override uri() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override hash() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override converter() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
			}
			
			return;
		}
		
		
		
	}
	
}