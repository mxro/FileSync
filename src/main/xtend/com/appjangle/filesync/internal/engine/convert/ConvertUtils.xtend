package com.appjangle.filesync.internal.engine.convert

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.Query

import static extension de.mxro.async.Async.embed

class ConvertUtils {

	val labelTypes = #['https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel']

	val fileExtensions = #{
		"https://admin1.linnk.it/types/v01/isHtmlValue" -> ".html",
		"1" -> ".type",
		"2" -> ".css",
		"3" -> ".js"	
	}

	def getFileExtension(Node forNode, ValueCallback<String> cb) {

		val qry = forNode.selectAllLinks()

		qry.catchExceptions([er|cb.onFailure(er.exception())])

		qry.get [ links |
			for (mapping : fileExtensions.entrySet) {
				
				if (links.contains(mapping.key)) {
					cb.onSuccess(mapping.value)
					return
				}
				
			}
		]

	}

	

	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label).appendSafe(toNode.session().link(labelTypes.get(0)), "./label")
	}

	def appendTypes(Query toNode, FileItem source) {

		val ext = source.extension
		
		if (ext == ".html") {
			
			toNode.appendSafe(toNode.session().link('https://admin1.linnk.it/types/v01/isHtmlValue'))
			toNode.appendSafe(toNode.session().link('https://u1.linnk.it/6wbnoq/Types/aTemplate'))	
		}
		
		toNode.appendSafe(toNode.session().link('https://u1.linnk.it/6wbnoq/Types/aTextValue'))
		
	}
	

	public static val NO_VALUE = new Object()

	def getFileName(Node forNode, FileItem inFolder, String fileExtension, ValueCallback<String> cb) {
		getFileName(forNode,
			cb.embed(
				[ fileNameFromNode |
					var fileName = fileNameFromNode + fileExtension
					var idx = 1
					while (inFolder.getChild(fileName).exists) {
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

}
