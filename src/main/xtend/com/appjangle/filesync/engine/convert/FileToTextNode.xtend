package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.FileToNodes
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Closure
import io.nextweb.Node
import java.util.LinkedList
import java.util.List

class FileToTextNode implements FileToNodes {
	
	override update(FileItem source, Node node, ValueCallback<List<Closure<NetworkOperationContext>>> cb) {
		
		val content = source.text
		
		val ops = new LinkedList<NetworkOperation>
		
		val operation = [ ctx |
			
		]
		
		ops.add(operation)
		
		
	}
	
}