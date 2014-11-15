package com.appjangle.filesync.internal.engine.metadata.v01

import com.appjangle.filesync.ItemMetadata
import java.util.Date

class ItemXml implements ItemMetadata {
	
	
	public var String name;
	public var Date lastModified;
	public var String uri;
	public var String hash;
	public var String converter;
	
	override name() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override lastModified() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override uri() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override hash() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override converter() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}