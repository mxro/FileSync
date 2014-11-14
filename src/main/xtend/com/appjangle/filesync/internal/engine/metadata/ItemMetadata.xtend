package com.appjangle.filesync.internal.engine.metadata

import java.util.Date

interface ItemMetadata {
	
	def String name()
	
	def Date lastModified()
	
	def String uri()
	
	def String hash()
	
	
}