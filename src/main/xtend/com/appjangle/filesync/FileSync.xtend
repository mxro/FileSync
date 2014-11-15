package com.appjangle.filesync

import com.appjangle.filesync.internal.engine.SyncFolder
import com.appjangle.filesync.internal.engine.convert.ConverterCollection
import com.appjangle.filesync.internal.engine.convert.FileToTextNode
import com.appjangle.filesync.internal.engine.convert.FolderToNode
import de.mxro.file.FileItem
import io.nextweb.Node

class FileSync {
	
	def static sync(FileItem folder, Node node) {
		
		new SyncFolder(folder, node, createDefaultConverter)
		
	}
	
	
	def static createDefaultConverter() {
		
		val coll = new ConverterCollection
		
		coll.addConverter(new FileToTextNode)
		coll.addConverter(new FolderToNode)
		
		coll
		
	}
	
}