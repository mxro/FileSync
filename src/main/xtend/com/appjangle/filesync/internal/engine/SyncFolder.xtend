package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Metadata
import com.appjangle.filesync.SyncParams
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
		if (!params.settings.upload) {
			download(cb)
			return
		}
	
	
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
		if (!params.settings.download) {
			cb.onSuccess(Success.INSTANCE)
			return;
		}
		
		new NetworkToFileOperations(params, metadata).determineOps(cb.embed([ ops |
			
			ops.execute(params.folder, metadata)
			
			params.folder.saveMetadata(metadata)
			
			cb.onSuccess(Success.INSTANCE)
		]))
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}