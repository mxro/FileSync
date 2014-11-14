package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.NetworkOperation
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.LinkedList
import java.util.List
import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.engine.metadata.Metadata
import com.appjangle.filesync.engine.metadata.ItemMetadata
import mx.gwtutils.MxroGWTUtils

import static extension de.mxro.async.Async.embed

class FileToTextNode implements Converter {

	extension ConvertUtils utils = new ConvertUtils()

	override worksOn(FileItem source) {

		val name = source.name

		name.endsWith('.txt') || name.endsWith('.xml')

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
					baseNode.appendLabel(nameWithoutExtension)
				)
			])

		cb.onSuccess(ops)
	}

	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {

		val content = source.text

		val address = metadata.getChild(source.getName).uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				newArrayList(ctx.session.link(address).setValueSafe(content))
			])

		cb.onSuccess(ops);

	}

	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		val address = cachedFile.uri

		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				newArrayList(ctx.parent.removeSafe(ctx.session.link(address)))
			])

		cb.onSuccess(ops);
	}

	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		
		source.getFileName(cb.embed([fileName |
			
			
			
		]))
	}
		

}
