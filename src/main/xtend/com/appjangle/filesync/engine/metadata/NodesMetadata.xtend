package com.appjangle.filesync.engine.metadata

import java.util.List

interface NodesMetadata {
	
	
	def List<FileItemMetadata> getChildren()
	
	def FileItemMetadata getChild(String name)
	
}