package com.appjangle.filesync


import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

interface FileToNodes {
	
	def void update(FileItem source, Node node, ValueCallback<List<NetworkOperation>> cb);
	
}