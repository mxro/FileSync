package com.appjangle.filesync.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Success
import io.nextweb.Node
import java.util.List


import static extension de.mxro.async.Async.embed

class SyncFolder {
	
	val FileItem folder
	val Node node
	val Converter converter
	
	new(FileItem folder, Node node, Converter converter) {
		this.folder = folder
		this.node = node
		this.converter = converter
	}
	
	var Metadata metadata 

	def doIt(ValueCallback<Success> cb) {
		
		if (!folder.hasMetadata) {
			
			download(cb)
			return;
		}
		
		metadata = folder.assertMetadata
		
		new FileToNetworkOperations(node, folder, metadata, converter).determineOps(cb.embed [ ops |
			ops.execute(node, cb.embed [ 
				download(cb)
			])
		])
			
			
			
		
	}
	
	def download(ValueCallback<Success> cb) {
		metadata = folder.assertMetadata
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}