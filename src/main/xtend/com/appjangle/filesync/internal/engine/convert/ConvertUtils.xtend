package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.internal.engine.N
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.Query

import static extension de.mxro.async.Async.embed

class ConvertUtils {

	val labelTypes = #[N.LABEL]

	val textValueExtensions = #{
		N.HTML_VALUE -> '.html',
		N.TYPE -> '.type',
		N.CSS -> '.css',
		N.JAVASCRIPT -> '.js',
		N.COFFEESCRIPT -> '.coffee'	
	}

	def isTextValue(String fileName) {
		
		
		textValueExtensions.keySet.contains(fileName)
	}

	def textNodeTypes() {
		textValueExtensions.keySet
	}

	def getFileExtension(Node forNode, ValueCallback<String> cb) {

		val qry = forNode.selectAllLinks()

		qry.catchExceptions([er|cb.onFailure(er.exception())])

		qry.get [ links |
			for (mapping : textValueExtensions.entrySet) {
				
				if (links.contains(mapping.key)) {
					cb.onSuccess(mapping.value)
					return
				}
				
			}
		]

	}

	

	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label, "./.label").appendSafe(toNode.session().LABEL)
	}

	def appendTypesAndIcon(Query toNode, FileItem source) {

		val session = toNode.session()

		val ext = source.extension
		
		if (ext == ".html") {
			
			toNode.appendSafe(session.HTML_VALUE)
			toNode.appendSafe(session.TEMPLATE)
			
			toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon")
			.appendSafe(session.ICON)
				
		} else if (ext == ".js") {
			
			toNode.appendSafe(session.JAVASCRIPT)
			toNode.appendSafe(session.TEMPLATE)
			
			toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon").appendSafe(session.ICON)
			
		} else if (ext == ".coffee") {
			
			toNode.appendSafe(session.COFFEESCRIPT)
			toNode.appendSafe(session.TEMPLATE)
			
			toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon").appendSafe(session.ICON)
			
		} else if (ext == ".css") {
			
			toNode.appendSafe(session.CSS)
			
			toNode.appendSafe(session.TEMPLATE)
			
			toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon").appendSafe(session.ICON)
			
		} else if (ext == ".type") {
			
			toNode.appendSafe(session.TYPE)
			
			toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon").appendSafe(session.ICON)
			
			toNode.appendSafe("").appendSafe(session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9"));
			
		}
		
		toNode.appendSafe(session.link(N.TEXT_VALUE))
		
	}
	

	public static val NO_VALUE = new Object()

	def getFileName(Node forNode, FileItem inFolder, String fileExtension, ValueCallback<String> cb) {
		getFileName(forNode,
			cb.embed(
				[ fileNameFromNode |
					var fileName = fileNameFromNode + fileExtension
					var idx = 1
					while (inFolder.get(fileName).exists) {
						fileName = fileNameFromNode + idx + fileExtension
						idx++
					}
					cb.onSuccess(fileName)
				]))
	}

	def getFileName(Node fromNode, ValueCallback<String> cb) {

		val cbs = Async.collect(labelTypes.size,
			cb.embed(
				[ res |
					for (item : res) {
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
			qry.catchUndefined([itmcb.onSuccess(ConvertUtils.NO_VALUE)])
			qry.catchExceptions([er|itmcb.onFailure(er.exception)])
			qry.get [ label |
				itmcb.onSuccess(label.value())
			]
		]

	}

	def static getNameFromUri(String uri) {
		uri.substring(uri.lastIndexOf("/") + 1)
	}
	
	extension N n = new N

}
