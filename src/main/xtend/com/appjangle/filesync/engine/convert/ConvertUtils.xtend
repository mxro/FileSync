package com.appjangle.filesync.engine.convert

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import io.nextweb.Node
import io.nextweb.Query

import static extension de.mxro.async.Async.embed

class ConvertUtils {
	
	val labelTypes = #['https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel']
	
	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label).appendSafe(
						toNode.session().link(labelTypes.get(0)), "./label")
	}
	
	
	def getFileName(Node fromNode, ValueCallback<String> cb) {
		
		val cbs = Async.collect(labelTypes.size, cb.embed([ res |
			
		]));
		
		labelTypes.forEach [ labelType | 
			
			val qry = fromNode.select(fromNode.session().link(labelType))
			
			val itmcb = cbs.createCallback
			
			qry.catchUndefined([ur |itmcb.onSuccess(false)])
			
		]
		
		
	}
	
}