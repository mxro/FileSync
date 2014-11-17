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
					cb.onSuccess(Success.INSTANCE)
				]))

		for (NetworkOperation op : ops) {

			op.apply(ctx,
				cb.embed [ qries |
					//println('exec '+qries)
					val opscbsitem = opscbs.createCallback
					
					val cbs = Async.collect(qries.size,
						cb.embed(
							[
								opscbsitem.onSuccess(Success.INSTANCE)
							]))
					
					for (qry : qries) {
						val itmcb = cbs.createCallback

						if (qry instanceof Query) {
							
							println('exec '+qry)
							//val safeQry = qry as Query
							//val res = onNode.session().promise(safeQry)
							qry.catchExceptions([er|itmcb.onFailure(er.exception)])
							qry.get([succ|
								println('success '+qry)
								itmcb.onSuccess(Success.INSTANCE)
							])

						} else if (qry instanceof NextwebPromise<?>) {
							println('run promise '+qry)
							val safeQry = qry as NextwebPromise<Object>
							//val res = onNode.session().promise(safeQry)
							safeQry.catchExceptions([er|itmcb.onFailure(er.exception)])
							safeQry.get([succ|itmcb.onSuccess(Success.INSTANCE)])
						} else {
							
							throw new RuntimeException('Unsupported pending query: '+qry.class)
							
						}
					}
				])

		}

		onNode.session().commit

	}

}
