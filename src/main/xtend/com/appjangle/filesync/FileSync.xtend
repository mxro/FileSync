package com.appjangle.filesync

import com.appjangle.filesync.internal.engine.FileUtils
import com.appjangle.filesync.internal.engine.SyncFolder
import com.appjangle.filesync.internal.engine.convert.ConverterCollection
import com.appjangle.filesync.internal.engine.convert.FileToTextNode
import com.appjangle.filesync.internal.engine.convert.FolderToNode
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.file.Jre.FilesJre
import de.mxro.fn.Success
import io.nextweb.Node
import java.io.File

import static extension de.mxro.async.Async.embed

class FileSync {

	/**
	 * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
	 */
	def static syncSingleFolder(File folder, Node node, ValueCallback<Success> cb) {
		syncSingleFolder(FilesJre.wrap(folder), node, cb)

	}
	
	/**
	 * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
	 */
	def static syncSingleFolder(FileItem folder, Node node, ValueCallback<Success> cb) {

		new SyncFolder(folder, node, createDefaultConverter).doIt(cb)

	}

	/**
	 * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
	 */
	def static sync(FileItem folder, Node node, ValueCallback<Success> cb) {

		syncSingleFolder(folder, node,
			cb.embed [
				val toSync = folder.children.filter[
					isDirectory && visible && !name.startsWith('.')  ]
				Async.forEach(
					toSync.toList,
					[ childFolder, itmcb |
						
						val metadata = folder.loadMetadata
						
						val itmmetadata = metadata.get(childFolder.name)
						
						val qry = node.session().link(itmmetadata.uri)
						
						qry.catchExceptions [er|cb.onFailure(er.exception)]
							
						qry.get [childNode |
							syncSingleFolder(childFolder, node, itmcb)
						
						]
						
						
					cb.embed[cb.onSuccess(Success.INSTANCE)])
				])

		}

		def static createDefaultConverter() {

			val coll = new ConverterCollection

			coll.addConverter(new FileToTextNode)
			coll.addConverter(new FolderToNode)

			coll

		}

static extension FileUtils fileUtils = new FileUtils

	}
	