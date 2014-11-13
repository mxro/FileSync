package com.appjangle.filesync.engine

import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node

interface FolderSynchronization {
	
	def void nodeToFolder(Node node, FileItem file, ValueCallback<FolderSynchronizationResult> cb)
	
}