package com.appjangle.filesync.internal.engine.metadata.v01

import com.appjangle.filesync.ItemMetadata
import java.util.Date
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode

@EqualsHashCode
class ItemXml implements ItemMetadata {
	
	@Accessors String name
	@Accessors Date lastModified
	@Accessors String uri
	@Accessors String hash
	@Accessors String converter
	
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