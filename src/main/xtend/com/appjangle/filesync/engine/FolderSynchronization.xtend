package com.appjangle.filesync.engine

import io.nextweb.Node
import java.io.File

interface FolderSynchronization {
	
	def void nodeToFolder(Node node, File file)
	
}