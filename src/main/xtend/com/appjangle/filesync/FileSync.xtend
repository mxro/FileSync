package com.appjangle.filesync

import com.appjangle.filesync.internal.engine.FileUtils
import com.appjangle.filesync.internal.engine.SyncFolder
import com.appjangle.filesync.internal.engine.convert.ConverterCollection
import com.appjangle.filesync.internal.engine.convert.FileToTextNode
import com.appjangle.filesync.internal.engine.convert.FolderToNode
import com.appjangle.filesync.internal.engine.convert.FolderToNothing
import com.appjangle.filesync.internal.engine.convert.NodeToNothing
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.file.Jre.FilesJre
import de.mxro.fn.Success
import io.nextweb.Node
import io.nextweb.nodes.Token
import java.io.File

import static extension de.mxro.async.Async.embed

class FileSync {

	def static syncSingleFolder(SyncParams params, ValueCallback<Success> cb) {
		new SyncFolder(params).doIt(cb)
	}

	def static defaultSyncParams() {
		val params = new SyncParams

		params.converter = createDefaultConverter
		params.settings = new SynchronizationSettings
		params.state = new SynchronizationState() {
		}

		return params
	}

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
		val params = defaultSyncParams

		params.folder = folder
		params.node = node

		syncSingleFolder(params, cb)

	}

	def static void sync(SyncParams params, ValueCallback<Success> cb) {
		syncSingleFolder(params,
			cb.embed [
				val toSync = params.folder.children.filter[isDirectory && visible && !name.startsWith('.')]
				Async.forEach(toSync.toList,
					[ childFolder, itmcb |
						val metadata = params.folder.loadMetadata
						val itmmetadata = metadata.get(childFolder.name)
						val qry = params.node.session().link(itmmetadata.uri)
						qry.catchExceptions[er|itmcb.onFailure(er.exception)]
						qry.get [ childNode |
							val childParams = new SyncParams(params)
							childParams.folder = childFolder
							childParams.node = childNode
							sync(childParams, itmcb)
						]
					],
					cb.embed [
						cb.onSuccess(Success.INSTANCE)
					])
			])
	}

	/**
	 * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
	 */
	def static void sync(FileItem folder, Node node, ValueCallback<Success> cb) {
		val params = defaultSyncParams

		params.folder = folder
		params.node = node

		sync(params, cb)
	}

	def static createDefaultConverter() {

		val coll = new ConverterCollection

		coll.addConverter(new FileToTextNode)
		
		coll.addConverter(new NodeToNothing [node, cb |
			cb.onSuccess(node.value() instanceof Token)
		])
		
		coll.addConverter(new FolderToNothing [file |
			file.name.startsWith(".") || !file.visible
		])
		
		coll.addConverter(new FolderToNode)

		coll

	}

	static extension FileUtils fileUtils = new FileUtils

}
