package com.appjangle.filesync.internal.engine.metadata

import io.nextweb.Node
import java.util.List

interface Metadata {
	
	def List<com.appjangle.filesync.engine.metadata.ItemMetadata> getChildren()
	
	def com.appjangle.filesync.engine.metadata.ItemMetadata get(String name)
	
	def com.appjangle.filesync.engine.metadata.ItemMetadata get(Node forNode)
	
	def com.appjangle.filesync.engine.metadata.Metadata add(com.appjangle.filesync.engine.metadata.ItemMetadata itemMetadata)
	
	def com.appjangle.filesync.engine.metadata.Metadata update(com.appjangle.filesync.engine.metadata.ItemMetadata itemMetadata)
	
	def com.appjangle.filesync.engine.metadata.Metadata remove(String name)
	
}