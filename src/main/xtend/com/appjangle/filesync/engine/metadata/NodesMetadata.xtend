package com.appjangle.filesync.engine.metadata

import java.util.List

interface NodesMetadata {
	
	
	def List<FileItemMetaData> getChildren()
	
	def FileItemMetaData getChild(String name)
	
}