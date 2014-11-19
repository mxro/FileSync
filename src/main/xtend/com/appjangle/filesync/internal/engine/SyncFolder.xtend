package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Success
import io.nextweb.Node

import static extension de.mxro.async.Async.embed

class SyncFolder {
	
	val SyncParams params
	
	new(SyncParams params) {
		this.params = params
	}
	
	var Metadata metadata 

	def doIt(ValueCallback<Success> cb) {
	
		if (!params.folder.hasMetadata) {

			metadata = params.folder.assertMetadata
	
			download(cb)
			return;
		}
		
		metadata = params.folder.assertMetadata
		
		new FileToNetworkOperations(params, metadata).determineOps(cb.embed [ ops |
			ops.execute(params.node, cb.embed [ 
				
				download(cb)
			])
		])

		
	}
	
	
	
	def download(ValueCallback<Success> cb) {
		new NetworkToFileOperations(node, folder, metadata, converter).determineOps(cb.embed([ ops |
			
			ops.execute(folder, metadata)
			
			folder.saveMetadata(metadata)
			
			cb.onSuccess(Success.INSTANCE)
		]))
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}