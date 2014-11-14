package com.appjangle.filesync.engine.convert

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.Query

import static extension de.mxro.async.Async.embed

class ConvertUtils {
	
	val extensions = #{
		""->".html"
	}
	
	
	def getFileExtension(Node forNode, ValueCallback<String> cb) {
		
		
		
	}
	
	val labelTypes = #['https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel']
	
	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label).appendSafe(
						toNode.session().link(labelTypes.get(0)), "./label")
	}
	
	static val NO_LABEL = new Object()
	
	def getFileName(Node forNode,  FileItem inFolder, String fileExtension, ValueCallback<String> cb) {
		getFileName(forNode, cb.embed([ fileNameFromNode |
			var fileName = fileNameFromNode + fileExtension
			var idx = 1
			while (inFolder.getChild(fileName).exists) {
				fileName = fileNameFromNode+idx+fileExtension
				idx++
			}
			
			cb.onSuccess(fileName)
		]))
	}
	
	def getFileName(Node fromNode, ValueCallback<String> cb) {
		
		val cbs = Async.collect(labelTypes.size, cb.embed([ res |
			for (item: res) {
				if (item instanceof String) {
					cb.onSuccess(item)
					return
				}
				
			}

			// when no label defined
			cb.onSuccess(getNameFromUri(fromNode.uri()))
			
		]));
		
		labelTypes.forEach [ labelType | 
			
			val qry = fromNode.select(fromNode.session().link(labelType))
			
			val itmcb = cbs.createCallback
			
			qry.catchUndefined([ itmcb.onSuccess(NO_LABEL) ])
			
			qry.catchExceptions([ er | itmcb.onFailure(er.exception)])
			
			qry.get [ label | 
				itmcb.onSuccess(label.value())
			]
			
		]
		
		
	}
	
	def static getNameFromUri(String uri) {
		uri.substring(uri.lastIndexOf("/")+1)
	}
	
}