package com.appjangle.filesync

import com.appjangle.filesync.internal.engine.FileUtils
import com.appjangle.filesync.internal.engine.SyncFolder
import com.appjangle.filesync.internal.engine.convert.ConvertUtils
import com.appjangle.filesync.internal.engine.convert.ConverterCollection
import com.appjangle.filesync.internal.engine.convert.FileToTextNode
import com.appjangle.filesync.internal.engine.convert.FolderToNode
import com.appjangle.filesync.internal.engine.convert.FolderToNothing
import com.appjangle.filesync.internal.engine.convert.NodeToNothing
import de.mxro.async.AsyncCommon
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import de.mxro.file.Jre.FilesJre
import de.mxro.fn.Success
import io.nextweb.Link
import io.nextweb.Node
import io.nextweb.nodes.Token
import java.io.File
import java.util.LinkedList

import static extension de.mxro.async.AsyncCommon.embed

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
		params.notifications = new SyncNotifications

		params.syncRoots = new LinkedList

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

	private def static void syncInt(SyncParams params, ValueCallback<Success> cb) {
		if (params.state.wasSynced(params.node)) {
			params.notifications.onNodeSkippedBecauseItWasAlreadySynced(params.folder, params.node);
			cb.onSuccess(Success.INSTANCE);
			return;
		}

		params.state.addSynced(params.node);

		syncSingleFolder(params,
			cb.embed [
				val toSync = params.folder.children.filter[isDirectory && visible && !name.startsWith('.')]
				AsyncCommon.forEach(toSync.toList,
					[ childFolder, itmcb |
						val metadata = params.folder.loadMetadata
						val itmmetadata = metadata.get(childFolder.name)
						val isChild = itmmetadata.uri.startsWith(params.node.uri())
						var withinSyncRoots = false
						var Link matchedSyncRoot = null
						for (syncRoot : params.syncRoots) {
							if (itmmetadata.uri.startsWith(syncRoot.uri())) {
								withinSyncRoots = true
								matchedSyncRoot = syncRoot
							}
						}
						
						if (!isChild && !withinSyncRoots) {
							itmcb.onSuccess(Success.INSTANCE)
							return;

						}
						
						var inDontFollow = false;
						for (dontFollow : params.dontFollow) {
							if (itmmetadata.uri.equals(dontFollow)) {
								inDontFollow = true
							}
						}
						
						if (inDontFollow) {
							itmcb.onSuccess(Success.INSTANCE)
							return;
						}
						
						
						var Link qry
						if (withinSyncRoots && matchedSyncRoot.secret() !== null && matchedSyncRoot.secret().length > 0) {
							qry = params.node.session().link(itmmetadata.uri, matchedSyncRoot.secret())
						} else {
							qry = params.node.session().link(itmmetadata.uri)
						}
						qry.catchExceptions[er|itmcb.onFailure(er.exception)]
						qry.get [ childNode |
							val childParams = new SyncParams(params)
							childParams.folder = childFolder
							childParams.node = childNode
							// println("Processing "+childNode.uri())
							if (childNode.uri().startsWith("http://localhost")) {
								println("ERROR: Illegal node "+childNode.uri()+" with parent "+params.node.uri())
								itmcb.onSuccess(Success.INSTANCE)
								
								return;
							}
							
							syncInt(childParams, itmcb)
						]
					],
					cb.embed [
						cb.onSuccess(Success.INSTANCE)
					])
			])
	}

	static def void sync(SyncParams params, ValueCallback<Success> cb) {
		if (params.syncRoots.size() == 0) {
			params.syncRoots.add(params.node.session().link(params.node))
		}

		syncInt(params, cb)
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

		coll.addConverter(
			new NodeToNothing [ node, cb |
				cb.onSuccess(node.value() instanceof Token)
			])

		coll.addConverter(
			new NodeToNothing [ node, cb |
				cb.onSuccess(ConvertUtils.getNameFromUri(node.uri()).startsWith('.'))
			])

		coll.addConverter(
			new FolderToNothing [ file |
				file.name.startsWith(".") || !file.visible
			])

		coll.addConverter(new FolderToNode)

		coll

	}

	static extension FileUtils fileUtils = new FileUtils

}
