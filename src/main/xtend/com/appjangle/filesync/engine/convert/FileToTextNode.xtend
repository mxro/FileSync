package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.FileToNodes
import com.appjangle.filesync.NetworkOperation
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.Session
import java.util.LinkedList
import java.util.List

class FileToTextNode implements FileToNodes {
	
	override update(FileItem source, Node node, ValueCallback<List<NetworkOperation>> cb) {
		
		val content = source.text
		
		val ops = new LinkedList<NetworkOperation>
		
		val operation = [ Session session, Node onNode |
			
		]
		
		ops.add(operation)
		
		
	}
	
}