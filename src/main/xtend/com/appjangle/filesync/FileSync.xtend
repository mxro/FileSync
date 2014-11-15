package com.appjangle.filesync

import com.appjangle.filesync.internal.engine.SyncFolder
import com.appjangle.filesync.internal.engine.convert.ConverterCollection
import com.appjangle.filesync.internal.engine.convert.FileToTextNode
import com.appjangle.filesync.internal.engine.convert.FolderToNode
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.Jre.FilesJre
import de.mxro.fn.Success
import io.nextweb.Node
import java.io.File

class FileSync {
	
	def static sync(File folder, Node node, ValueCallback<Success> cb) {
		
		new SyncFolder(FilesJre.wrap(folder), node, createDefaultConverter).doIt(cb)
		
	}
	
	def static createDefaultConverter() {
		
		val coll = new ConverterCollection
		
		coll.addConverter(new FileToTextNode)
		coll.addConverter(new FolderToNode)
		
		coll
		
	}
	
}