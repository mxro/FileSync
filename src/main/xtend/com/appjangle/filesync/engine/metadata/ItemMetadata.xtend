package com.appjangle.filesync.engine.metadata

import java.util.Date

interface ItemMetadata {
	
	def String name()
	
	def Date lastModified()
	
	def String uri()
	
}