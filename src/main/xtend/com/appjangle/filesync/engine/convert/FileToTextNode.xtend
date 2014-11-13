package com.appjangle.filesync.engine.convert

import com.appjangle.filesync.Convert
import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.NetworkOperationContext
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.fn.Closure
import io.nextweb.Node
import java.util.LinkedList
import java.util.List

class FileToTextNode implements Convert {

	override update(FileItem source, Node node, ValueCallback<List<NetworkOperation>> cb) {

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

}
