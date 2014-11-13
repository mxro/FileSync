package com.appjangle.filesync

import com.appjangle.filesync.engine.metadata.FileItemMetaData
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

interface Convert {
	
	def void update(NodesMetadata metadata, FileItem source, Node parent, ValueCallback<List<NetworkOperation>> cb)
	
	
	def void createNodes(NodesMetadata metadata,FileItem source, Node parent, ValueCallback<List<NetworkOperation>> cb)
	
	
	def void deleteNodes(NodesMetadata metadata, FileItemMetaData cachedFile, Node parent, ValueCallback<List<NetworkOperation>> cb);
	
	
}