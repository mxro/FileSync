package com.appjangle.filesync

import io.nextweb.Node
import io.nextweb.Session

interface NetworkOperationContext {
	def Session session()
	def Node parent()
}