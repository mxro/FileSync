package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Node
import io.nextweb.Query
import io.nextweb.promise.NextwebPromise
import java.util.List

import de.mxro.async.AsyncCommon
import static extension de.mxro.async.AsyncCommon.embed

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

		val opscbs = de.mxro.async.AsyncCommon.collect(ops.size,
			cb.embed(
				[
					cb.onSuccess(Success.INSTANCE)
				]))

		for (NetworkOperation op : ops) {

			op.apply(ctx,
				cb.embed [ qries |
					//println('exec '+qries)
					val opscbsitem = opscbs.createCallback
					
					val cbs = de.mxro.async.AsyncCommon.collect(qries.size,
						cb.embed(
							[
								opscbsitem.onSuccess(Success.INSTANCE)
							]))
					
					for (qry : qries) {
						val itmcb = cbs.createCallback

						if (qry instanceof Query) {
							
							qry.catchExceptions [er|itmcb.onFailure(er.exception)]
							qry.get([succ|
								
								itmcb.onSuccess(Success.INSTANCE)
							])

						} else if (qry instanceof NextwebPromise<?>) {
							
							val safeQry = qry as NextwebPromise<Object>
							//val res = onNode.session().promise(safeQry)
							safeQry.catchExceptions [er|itmcb.onFailure(er.exception)]
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
