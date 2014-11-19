package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Metadata
import de.mxro.file.FileItem
import io.nextweb.Node

class SyncValueOperations {
	
	def downloadValue(Node node, Metadata metadata, FileItem folder) {
		
		if (!folder.get("value.txt").exists) {
			folder.createFile("value.txt").text = node.value().toString()
			return;
		}
		
		
		
	}
	
}