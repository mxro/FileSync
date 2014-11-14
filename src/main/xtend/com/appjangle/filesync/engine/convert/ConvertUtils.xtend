package com.appjangle.filesync.engine.convert

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import io.nextweb.Node
import io.nextweb.Query

class ConvertUtils {
	
	val labelTypes = #['https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel']
	
	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label).appendSafe(
						toNode.session().link(labelTypes.get(0)), "./label")
	}
	
	
	def getFileName(Node fromNode, ValueCallback<String> cb) {
		
		
		Async.collect(labelTypes.size, Async.embed(cb, [ res |
			
		]));
		
		
	}
	
}