package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.Convert
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.FileItemMetaData
import com.appjangle.filesync.engine.metadata.NodesMetadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.LinkedList
import java.util.List

class FileToTextNode implements Convert {

	def fileNameToAddress(String fileName) {
		return fileName
	}

	override update(NodesMetadata metadata, FileItem source, Node parent, ValueCallback<List<NetworkOperation>> cb) {

		val content = source.text

		val ops = new LinkedList<NetworkOperation>
		
		
		
		ops.add(
			[ ctx |
				
				
				
				ctx.node.setValueSafe(content).catchExceptions(
					[ er |
						cb.onFailure(er.exception())
					])
			])

		cb.onSuccess(ops);

	}
	
	override createNodes(NodesMetadata metadata, FileItem source, Node parent, ValueCallback<List<NetworkOperation>> cb) {
		
	}
	
	override deleteNodes(NodesMetadata metadata, FileItemMetaData cachedFile, Node parent, ValueCallback<List<NetworkOperation>> cb) {
		
	}

}
