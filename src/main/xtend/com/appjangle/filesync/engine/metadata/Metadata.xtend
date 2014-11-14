package com.appjangle.filesync.engine.metadata

import java.util.List

interface Metadata {
	
	
	def List<ItemMetadata> getChildren()
	
	def ItemMetadata getChild(String name)
	
}