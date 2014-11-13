package com.appjangle.filesync

import com.appjangle.filesync.engine.metadata.FileItemMetaData
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

interface Convert {
	
	def void update(FileItem source, Node node, ValueCallback<List<NetworkOperation>> cb)
	
	
	def void createNodes(FileItem source, Node node, ValueCallback<List<NetworkOperation>> cb)
	
	
	def void deleteNodes(FileItemMetaData cachedFile, Node node, ValueCallback<List<NetworkOperation>> cb);
	
	
}