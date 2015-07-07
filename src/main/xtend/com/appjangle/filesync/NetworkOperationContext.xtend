package com.appjangle.filesync

import io.nextweb.Node
import io.nextweb.Client

interface NetworkOperationContext {
	def Client session()
	def Node parent()
}