package com.appjangle.filesync.engine.metadata

import java.util.Date

interface FileItemMetaData {
	
	def String name()
	
	def Date lastModified()
	
	
}