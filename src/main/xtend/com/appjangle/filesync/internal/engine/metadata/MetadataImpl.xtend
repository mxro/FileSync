package com.appjangle.filesync.internal.engine.metadata

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import io.nextweb.Node
import java.util.List

class MetadataImpl implements Metadata {
	
	List<ItemMetadata> items;
	
	override getChildren() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override get(String name) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override get(Node forNode) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override add(ItemMetadata itemMetadata) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override update(ItemMetadata itemMetadata) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override remove(String name) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}