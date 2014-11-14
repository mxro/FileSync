package com.appjangle.filesync.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.async.callbacks.SimpleCallback
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

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

	def doIt(SimpleCallback cb) {
		
		if (!folder.hasMetadata) {
			metadata = folder.assertMetadata
			fullDownload
			return;
		}
		
		metadata = folder.assertMetadata
		
		new FileToNetworkOperations(node, folder, metadata, converter).determineOps(new ValueCallback<List<NetworkOperation>>() {
			
			override onSuccess(List<NetworkOperation> value) {
				
			}
			
			override onFailure(Throwable t) {
				cb.onFailure(t)
			}
			
		})
		
	}
	
	def fullDownload() {
		
	}
	
	extension FileUtils fileUtils = new FileUtils()
	extension NetworkUtils networkUtils = new NetworkUtils()
	
}