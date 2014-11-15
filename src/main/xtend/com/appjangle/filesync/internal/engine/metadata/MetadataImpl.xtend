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

		items.findFirst[  it.uri == forNode.uri() ]
	}
	
	override add(ItemMetadata itemMetadata) {
		items.add(itemMetadata.toXml)
		
		this
	}
	
	override update(ItemMetadata itemMetadata) {
		val foundIdx = indexOfChild(itemMetadata.name)
		
		if (foundIdx == -1) {
			throw new RuntimeException("Cannot update child which is not defined: "+itemMetadata.name);
		}
		
		items.set(foundIdx, itemMetadata.toXml)
		
		this
	}
	
	override remove(String name) {
		val foundIdx = indexOfChild(name)
		
		if (foundIdx == -1) {
			throw new RuntimeException("Cannot remove child which is not defined: "+name);
		}
		
		items.remove(foundIdx)
		
		this
	}
	
	private def toXml(ItemMetadata itemMetadata) {
		val item = new ItemXml
		item.name = itemMetadata.name
		item.uri = itemMetadata.uri
		item.lastModified =itemMetadata.lastModified
		item.hash = itemMetadata.hash
		item.converter = itemMetadata.converter
		item
	}
	
	private def indexOfChild(String name) {
		var foundIdx = -1;
		for (i:0..<items.size) {
			val item = items.get(i)
			
			if (item.name == name) {
				foundIdx = i
			}
		}
		
		foundIdx
	} 
}