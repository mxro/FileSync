package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Success
import io.nextweb.Node

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
			metadata = folder.assertMetadata
			download(cb)
			return;
		}
		
		metadata = folder.assertMetadata
		
		new FileToNetworkOperations(node, folder, metadata, converter).determineOps(cb.embed [ ops |
			ops.execute(node, cb.embed [ 
				download(cb.embed [
					
					metadata.save
					cb.onSuccess(Success.INSTANCE)
					
				])
			])
		])
			
			
			
		
	}
	
	def save(Metadata metadata) {
		nkjkjnhk
	}
	
	def download(ValueCallback<Success> cb) {
		new NetworkToFileOperations(node, folder, metadata, converter).determineOps(cb.embed([ ops |
			ops.execute(folder, metadata)
			
			cb.onSuccess(Success.INSTANCE)
		]))
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}