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
		name
	}
	
	override lastModified() {
		lastModified
	}
	
	override uri() {
		uri
	}
	
	override hash() {
		hash
	}
	
	override converter() {
		converter
	}
	
}