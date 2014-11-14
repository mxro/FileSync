package com.appjangle.filesync

import com.appjangle.filesync.engine.metadata.FileItemMetadata
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

interface Converter {

	def boolean worksOn(FileItem source)
	
	def void worksOn(Node node, ValueCallback<Boolean> cb)
	
	def void createNodes(NodesMetadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb)
	
	def void update(NodesMetadata metadata, FileItem source,  ValueCallback<List<NetworkOperation>> cb)
	
	def void deleteNodes(NodesMetadata metadata, FileItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb)
	
	def void createFiles(NodesMetadata metadata, Node source, ValueCallback<List<FileOperation>> cb)
}