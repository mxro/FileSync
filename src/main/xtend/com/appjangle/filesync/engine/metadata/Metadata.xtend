package com.appjangle.filesync.engine.metadata

import io.nextweb.Node
import java.util.List

interface Metadata {
	
	
	def List<ItemMetadata> getChildren()
	
	def ItemMetadata getChild(String name)
	
	def ItemMetadata getChild(Node forNode)
	
	def Metadata add(ItemMetadata itemMetadata)
	
}