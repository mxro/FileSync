package com.appjangle.filesync.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Node
import io.nextweb.promise.NextwebPromise
import java.util.List

import static extension de.mxro.async.Async.embed

class NetworkUtils {

	def execute(List<NetworkOperation> ops, Node onNode, ValueCallback<Success> cb) {
	
		val ctx = new NetworkOperationContext() {
				
				override session() {
					onNode.session()
				}
				
				override parent() {
					onNode
				}
				
			};
	
		for (NetworkOperation op : ops) {

			val qries = op.apply(ctx)
			
			val cbs = Async.collect(qries.size, cb.embed([
				cb.onSuccess(Success.INSTANCE)
			]))
			
			
			for ( qry: qries) {
				val itmcb = cbs.createCallback
				
				val safeQry = qry as NextwebPromise<Object>
				
				val res = onNode.session().promise(safeQry)
				
				res.catchExceptions([er | itmcb.onFailure(er.exception) ])

				res.get([ Object succ | itmcb.onSuccess(Success.INSTANCE) ])
				
			}
			
			
		}
		
	}
	
}