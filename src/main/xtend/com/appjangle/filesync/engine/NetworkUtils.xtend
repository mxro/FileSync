package com.appjangle.filesync.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Node
import java.util.List

class NetworkUtils {

	def execute(List<NetworkOperation> ops, Node onNode, ValueCallback<Success> cb) {
		
		
		for (NetworkOperation op : ops) {
			
			
			val qries = op.apply(new NetworkOperationContext() {
				
				override session() {
					onNode.session()
				}
				
				override parent() {
					onNode
				}
				
			})
			
			for ( qry: qries) {
				
			}
			
			
		}
		
	}
	
}