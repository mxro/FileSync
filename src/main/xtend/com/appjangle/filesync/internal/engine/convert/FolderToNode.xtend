package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.LinkedList
import java.util.List
import mx.gwtutils.MxroGWTUtils

class FolderToNode implements Converter {
	
	override worksOn(FileItem source) {
		source.directory
	}
	
	override worksOn(Node node, ValueCallback<Boolean> cb) {
		cb.onSuccess(true)
	}
	
	override createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		val simpleName = MxroGWTUtils.getSimpleName(source.name)
		val ops = new LinkedList<NetworkOperation>

		ops.add(
			[ ctx |
				val baseNode = ctx.parent.appendSafe(source.name, "./" + simpleName)
				newArrayList(
					baseNode
				)
			])

		cb.onSuccess(ops)
	}
	
	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override removeFiles(FileItem folder, Metadata metadata, ItemMetadata item, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}