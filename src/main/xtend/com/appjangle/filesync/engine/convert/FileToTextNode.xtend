package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.ItemMetadata
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.Date
import java.util.LinkedList
import java.util.List
import mx.gwtutils.MxroGWTUtils

import static extension de.mxro.async.Async.embed

class FileToTextNode implements Converter {

	extension ConvertUtils utils = new ConvertUtils()

	override worksOn(FileItem source) {

		val name = source.name

		name.endsWith('.txt')

	}

	override worksOn(Node node, ValueCallback<Boolean> cb) {

		cb.onSuccess(node.value() instanceof String)

	}

	override createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {

		val nameWithoutExtension = MxroGWTUtils.removeExtension(source.name)

		val simpleName = MxroGWTUtils.getSimpleName(nameWithoutExtension)

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				val baseNode = ctx.parent.appendSafe(source.text, "./" + simpleName)
				newArrayList(
					baseNode,
					baseNode.appendLabel(nameWithoutExtension),
					baseNode.appendTypes(source)
				)
			])

		cb.onSuccess(ops)
	}

	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {

		val content = source.text

		val address = metadata.get(source.getName).uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				newArrayList(ctx.session.link(address).setValueSafe(content))
			])

		cb.onSuccess(ops)

	}

	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		val address = cachedFile.uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				newArrayList(ctx.parent.removeSafe(ctx.session.link(address)))
			])

		cb.onSuccess(ops)
	}

	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {

		source.getFileName(folder, '.txt', cb.embed([fileName |
			
			val ops = new LinkedList<FileOperation>
			
			ops.add(
				[ctx|
					val file = ctx.folder.createFile(fileName)
					
					file.text = source.value(String)
					
					ctx.metadata.add(new ItemMetadata() {
						
						override name() {
							fileName
						}
						
						override lastModified() {
							new Date() // TODO replace with last modified if available from node !!
						}
						
						override uri() {
							source.uri()
						}
						
						override hash() {
							file.hash
						}
						
					})
					
				]
			)
			
			cb.onSuccess(ops)
			
		]))
	}
	
	override updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		
		val fileName = metadata.get(source).name
		
		val content = source.value(String)
		
		val ops = new LinkedList<FileOperation>
		
		ops.add([ctx|
			
			val file = ctx.folder.getChild(fileName)
			
			if (file.text != content) {
			
				file.text = content
				
				ctx.metadata.update(new ItemMetadata() {
						
						override name() {
							fileName
						}
						
						override lastModified() {
							new Date() // TODO replace with last modified if available from node !!
						}
						
						override uri() {
							source.uri()
						}
						
						override hash() {
							file.hash
						}
						
					})
			
			}
			
			
			
		])
		
		cb.onSuccess(ops)
		
		
	}
	
	override removeFiles(FileItem folder, Metadata metadata, ItemMetadata item, ValueCallback<List<FileOperation>> cb) {
		val fileName = item.name
		
		val ops = new LinkedList<FileOperation>
		
		ops.add([ctx|
			
			ctx.folder.deleteFile(fileName)
			
			ctx.metadata.remove(fileName)
			
			
		])
		
		cb.onSuccess(ops)
	}
		

}
