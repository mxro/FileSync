package com.appjangle.filesync.engine.metadata

import java.util.Date

interface FileItemMetadata {
	
	def String name()
	
	def Date lastModified()
	
	def String uri()
	
}