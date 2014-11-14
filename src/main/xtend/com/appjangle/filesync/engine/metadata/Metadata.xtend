package com.appjangle.filesync.engine.metadata

import io.nextweb.Node
import java.util.List

interface Metadata {
	
	def List<ItemMetadata> getChildren()
	
	def ItemMetadata get(String name)
	
	def ItemMetadata get(Node forNode)
	
	def Metadata add(ItemMetadata itemMetadata)
	
	def Metadata update(ItemMetadata itemMetadata)
	
	def Metadata remove(String name)
	
}