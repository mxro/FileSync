package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Node
import io.nextweb.Query
import io.nextweb.promise.NextwebPromise
import java.util.List

import static extension de.mxro.async.Async.embed

class NetworkUtils {

	def execute(List<NetworkOperation> ops, Node onNode, ValueCallback<Success> cb) {
		
		println("run "+ops)
		
		val ctx = new NetworkOperationContext() {

			override session() {
				onNode.session()
			}

			override parent() {
				onNode
			}

		};

		val opscbs = Async.collect(ops.size,
			cb.embed(
				[
					println("Done!")
					cb.onSuccess(Success.INSTANCE)
				]))

		for (NetworkOperation op : ops) {

			val qries = op.apply(ctx)

			val opscbsitem = opscbs.createCallback

			val cbs = Async.collect(qries.size,
				cb.embed(
					[
						opscbsitem.onSuccess(Success.INSTANCE)
					]))

			for (qry : qries) {
				val itmcb = cbs.createCallback

				if (qry instanceof Query) {
					val safeQry = qry as Query
					val res = onNode.session().promise(safeQry)
					res.catchExceptions([er|itmcb.onFailure(er.exception)])
					res.get([succ|itmcb.onSuccess(Success.INSTANCE)])

				} else {
					
					val safeQry = qry as NextwebPromise<Object>
					val res = onNode.session().promise(safeQry)
					res.catchExceptions([er|itmcb.onFailure(er.exception)])
					res.get([succ|  itmcb.onSuccess(Success.INSTANCE)])
				}
			}

		}
		
		onNode.session().commit

	}

}
