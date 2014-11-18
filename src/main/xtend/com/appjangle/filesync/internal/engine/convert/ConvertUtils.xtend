package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.internal.engine.N
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Link
import io.nextweb.Node
import io.nextweb.Query
import io.nextweb.promise.Deferred
import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import mx.gwtutils.MxroGWTUtils

import static extension de.mxro.async.Async.embed
import io.nextweb.utils.data.NextwebDataExtension
import io.nextweb.Entity
import io.nextweb.Session

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
		val ext = MxroGWTUtils.getExtension(fileName)

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
				
				val list = new ArrayList<Deferred<?>>
				
				if (parent.session().link(parent).hasDirectChild(nodeToBeRemoved)) {

					parent.removeSafeRecursive(nodeToBeRemoved,
						opscb.embed [ res |
							
							list.addAll(res)
							opscb.onSuccess(list)
						])

				} else {
					
					list.add(parent.removeSafe(nodeToBeRemoved));
					opscb.onSuccess(list)
					
				}
			])

		cb.onSuccess(ops)
	}

	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label, "./.label").appendSafe(toNode.session().LABEL)
	}

	def List<Deferred<?>> appendTypesAndIcon(Query toNode, FileItem source) {
		val res = newArrayList
		
		val session = toNode.session()

		var ext = source.extension
		
		ext = '.'+ext
		
		if (ext == ".html") {
			
			res.add(toNode.appendSafe(session.HTML_VALUE))
			res.add(toNode.appendSafe(session.TEMPLATE))
			
			val icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon")
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
	extension NextwebDataExtension ext = new NextwebDataExtension
}
