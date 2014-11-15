package com.appjangle.filesync.internal.engine.metadata.v01

import java.io.Serializable
import java.util.List

class NodesXml implements Serializable {
	
	public val List<ItemXml> items
	
	new() {
		items = newArrayList
	}
	
}