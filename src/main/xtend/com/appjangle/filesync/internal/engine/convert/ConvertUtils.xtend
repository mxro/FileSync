package com.appjangle.filesync.internal.engine.convert

import com.appjangle.api.Link
import com.appjangle.api.Node
import com.appjangle.api.Query
import com.appjangle.api.operations.OperationsExtension
import com.appjangle.api.queries.QueriesExtension
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.internal.engine.FileUtils
import com.appjangle.filesync.internal.engine.N
import de.mxro.file.FileItem
import delight.async.AsyncCommon
import delight.async.callbacks.ValueCallback
import io.nextweb.promise.NextwebOperation
import io.nextweb.promise.utils.AsyncUtils
import java.util.ArrayList
import java.util.LinkedList
import java.util.List

import static extension delight.async.AsyncCommon.*

class ConvertUtils {

	val labelTypes = #[N.LABEL, N.LABEL2, N.LABEL3]

	val textValueExtensions = #{
		N.HTML_VALUE -> '.html',
		N.TYPE -> '.type',
		N.CSS -> '.css',
		N.JAVASCRIPT -> '.js',
		N.COFFEESCRIPT -> '.coffee',
		N.RICHTEXT -> '.htm'	
	}

	def isTextValue(String fileName) {
		val ext = fileName.getExtension

		textValueExtensions.containsValue('.'+ext)
	}

	def isTextType(Link link) {
		
		
		textValueExtensions.keySet.contains(link.uri())
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

	def deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		val address = cachedFile.uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx, opscb |
				metadata.remove(cachedFile.name)
				val nodeToBeRemoved = ctx.session.link(address)
				val parent = ctx.parent
				
				val list = new ArrayList<NextwebOperation<?>>
				
				if (parent.client().link(parent).hasDirectChild(nodeToBeRemoved)) {
					val innercb = AsyncUtils.wrap(nodeToBeRemoved.exceptionManager, opscb.embed [
							opscb.onSuccess(list)
						])
					parent.removeRecursive(nodeToBeRemoved,innercb)

				} else {
					
					list.add(parent.remove(nodeToBeRemoved));
					opscb.onSuccess(list)
					
				}
			])

		cb.onSuccess(ops)
	}

	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label, "./.label").appendSafe(toNode.client().LABEL)
	}

	def List<NextwebOperation<?>> appendTypesAndIcon(Query toNode, FileItem source) {
		val res = newArrayList
		
		val session = toNode.client()

		var ext = source.extension
		
		ext = '.'+ext
		
		if (ext == ".html") {
			
			res.add(toNode.appendSafe(session.HTML_VALUE))
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon")
			res.add(icon)
			res.add(icon.appendSafe(session.ICON))
				
		} else if (ext == ".htm") {
			res.add(toNode.appendSafe(session.RICHTEXT))
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141119/RTF.png", "./.icon")
			res.add(icon)
			res.add(icon.appendSafe(session.ICON))
			
		} else if (ext == ".js") {
			
			
			res.add(toNode.appendSafe(session.JAVASCRIPT))
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon");
			res.add(icon)
			res.add(icon.appendSafe(session.ICON))
			
		} else if (ext == ".coffee") {
			
			res.add(toNode.appendSafe(session.COFFEESCRIPT))
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon");
			
			res.add(icon)
			res.add(icon.appendSafe(session.ICON))
			
		} else if (ext == ".css") {
			
			res.add(toNode.appendSafe(session.CSS))
			
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon")
			
			res.add(icon)
			
			res.add(icon.appendSafe(session.ICON))
			
		} else if (ext == ".type") {
			
			res.add(toNode.appendSafe(session.TYPE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon")
			
			res.add(icon)
			res.add(icon.appendSafe(session.ICON))
			
			val description = toNode.appendSafe("")
			
			res.add(description)
			res.add(description.appendSafe(session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9")))
			
		}
		
		res.add(toNode.appendSafe(session.TEXT_VALUE))
		
	 	res
		
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

		val cbs = AsyncCommon.collect(labelTypes.size,
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
			val qry = fromNode.select(fromNode.client().link(labelType))
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
	extension OperationsExtension ext = new OperationsExtension
	extension QueriesExtension qxt = new QueriesExtension
	extension FileUtils futils = new FileUtils
}
