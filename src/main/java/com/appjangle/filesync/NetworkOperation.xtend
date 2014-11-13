package com.appjangle.filesync

import io.nextweb.Node
import io.nextweb.Session

interface NetworkOperation {
	
	
	def void define(Session session, Node node)
	
}