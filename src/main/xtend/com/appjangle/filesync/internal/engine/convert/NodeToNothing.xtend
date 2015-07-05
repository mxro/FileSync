package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import de.mxro.file.FileItem
import delight.async.callbacks.ValueCallback
import delight.functional.Closure2
import io.nextweb.Node
import java.util.List

class NodeToNothing implements Converter {
	
	val Closure2<Node, ValueCallback<Boolean>> test
	
	new(Closure2<Node, ValueCallback<Boolean>> test) {
		this.test = test
	}
	
	override worksOn(FileItem source) {
		false
	}
	
	override worksOn(Node node, ValueCallback<Boolean> cb) {
		test.apply(node, cb)
	} 
	
	override createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		throw new IllegalStateException("This operation should never be triggered for this converter.")
	}
	
	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		throw new IllegalStateException("This operation should never be triggered for this converter.")
	}
	
	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		throw new IllegalStateException("This operation should never be triggered for this converter.")
	}
	
	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		cb.onSuccess(newArrayList)
	}
	
	override updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		throw new IllegalStateException("This operation should never be triggered for this converter.")
	}
	
	override removeFiles(FileItem folder, Metadata metadata, ItemMetadata item, ValueCallback<List<FileOperation>> cb) {
		throw new IllegalStateException("This operation should never be triggered for this converter.")
	}
	
}