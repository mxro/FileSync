package com.appjangle.filesync.engine

import io.nextweb.Node
import io.nextweb.Session

interface NetworkOperation {
	
	
	def void define(Session session, Node node)
	
}