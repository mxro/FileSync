package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.internal.engine.FileUtils
import com.appjangle.filesync.internal.engine.N
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.promise.Deferred
import io.nextweb.utils.data.NextwebDataExtension
import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import mx.gwtutils.MxroGWTUtils

import static extension de.mxro.async.Async.embed

class FileToTextNode implements Converter {

	override worksOn(FileItem source) {

		source.name.isTextValue

	}

	override worksOn(Node node, ValueCallback<Boolean> cb) {

		val textNodeTypes = #[N.HTML_VALUE]
		
		val qry = node.selectAllLinks
		
		qry.catchExceptions([er | cb.onFailure(er.exception)])
		
		qry.get [links |
			for (link: links) {
				if (textNodeTypes.contains(link.uri())) {
					cb.onSuccess(true)
					return
				}
			}
			cb.onSuccess(false)
		]

	}

	override createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {

		val nameWithoutExtension = MxroGWTUtils.removeExtension(source.name)

		val simpleName = MxroGWTUtils.getSimpleName(nameWithoutExtension)

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx, opscb |
				val baseNode = ctx.parent.appendSafe(source.text, "./" + simpleName)
				
				metadata.add(new ItemMetadata() {
					
					override name() {
						source.name
					}
					
					override lastModified() {
						source.lastModified
					}
					
					override uri() {
						ctx.parent.uri()+"/"+simpleName
					}
					
					override hash() {
						source.hash
					}
					
					override converter() {
						FileToTextNode.this.toString
					}
					
				})
				
				opscb.onSuccess(newArrayList(
					baseNode,
					baseNode.appendLabel(nameWithoutExtension),
					baseNode.appendTypesAndIcon(source)
				))
			])

		cb.onSuccess(ops)
	}

	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {

		val content = source.text

		val address = metadata.get(source.getName).uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx, opscb |
				opscb.onSuccess(newArrayList(ctx.session.link(address).setValueSafe(content)))
			])

		cb.onSuccess(ops)

	}

	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		val address = cachedFile.uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx, opscb |
				
				metadata.remove(cachedFile.name)
				ctx.parent.removeSafeRecursive(ctx.session.link(address), opscb.embed [res|
					val list = new ArrayList<Deferred<?>>
					list.addAll(res)
					opscb.onSuccess(list)
				])
				
			])

		cb.onSuccess(ops)
	}

	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		source.getFileExtension(cb.embed([ext |
			source.getFileName(folder, ext, cb.embed([rawFileName |
			
			val fileName = rawFileName.toFileSystemSafeName(false, 100)
			
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
							file.lastModified // TODO replace with last modified if available from node !!
						}
						
						override uri() {
							source.uri()
						}
						
						override hash() {
							file.hash
						}
						
						override converter() {
							FileToTextNode.this.class.toString
						}
						
					})
					
				]
			)
			
			cb.onSuccess(ops)
			
		]))
		]))
		
	}
	
	override updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		
		val fileName = metadata.get(source).name
		
		val content = source.value(String)
		
		val ops = new LinkedList<FileOperation>
		
		
		
		ops.add([ctx|
			
			val file = ctx.folder.get(fileName)
			
			if (file.text != content) {
			
				file.text = content
				
				ctx.metadata.update(new ItemMetadata() {
						
						override name() {
							fileName
						}
						
						override lastModified() {
							file.lastModified // TODO replace with last modified if available from node !!
						}
						
						override uri() {
							source.uri()
						}
						
						override hash() {
							file.hash
						}
						
						override converter() {
							FileToTextNode.this.class.toString
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
		
		
		extension ConvertUtils cutils = new ConvertUtils
		extension FileUtils futils = new FileUtils
		extension NextwebDataExtension nutils = new NextwebDataExtension

}
