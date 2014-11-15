package com.appjangle.filesync.internal.engine.metadata

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import io.nextweb.Node
import java.util.List
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml

class MetadataImpl implements Metadata {
	
	val List<ItemMetadata> items
	
	new() {
		items = newArrayList
	}
	
	override getChildren() {
		items
	}
	
	override get(String name) {
		items.findFirst[ it.name == name ]
	}
	
	override get(Node forNode) {
		items.findFirst[ it.uri == forNode.uri() ]
	}
	
	override add(ItemMetadata itemMetadata) {
		items.add(itemMetadata.toXml)
		
		this
	}
	
	override update(ItemMetadata itemMetadata) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override remove(String name) {
		val item = get(name)
		
		
		
		this
	}
	
	def toXml(ItemMetadata itemMetadata) {
		val item = new ItemXml
		item.name = itemMetadata.name
		item.uri = itemMetadata.uri
		item.lastModified =itemMetadata.lastModified
		item.hash = itemMetadata.hash
		item
	}
	
	
}