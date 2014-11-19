package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import de.mxro.file.FileItem
import io.nextweb.Node
import de.mxro.async.callbacks.ValueCallback
import com.appjangle.filesync.Metadata
import java.util.List
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.FileOperation

class NodeToNothing implements Converter {
	
	override worksOn(FileItem source) {
		false
	}
	
	override worksOn(Node node, ValueCallback<Boolean> cb) {
		
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