package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success

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
		new NetworkToFileOperations(params, metadata).determineOps(cb.embed([ ops |
			
			ops.execute(params.folder, metadata)
			
			params.folder.saveMetadata(metadata)
			
			cb.onSuccess(Success.INSTANCE)
		]))
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}