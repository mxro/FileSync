package com.appjangle.filesync

import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Closure
import io.nextweb.Node
import java.util.List

interface FileToNodes {
	
	def void update(FileItem source, Node node, ValueCallback<List<Closure<NetworkOperationContext>>> cb);
	
}