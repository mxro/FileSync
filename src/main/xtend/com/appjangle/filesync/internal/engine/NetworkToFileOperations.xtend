package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.SyncParams
import de.mxro.async.Async
import de.mxro.async.Value
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.collections.CollectionsUtils
import io.nextweb.Link
import io.nextweb.Node
import java.util.ArrayList
import java.util.List

import static extension de.mxro.async.Async.*

/**
 * Determines operations to be performed on local files based on remote changes made in the cloud.
 */
class NetworkToFileOperations {

	val SyncParams params
	val Metadata metadata;

	new(SyncParams params, Metadata metadata) {
		this.params = params
		this.metadata = metadata

	}

	def determineOps(ValueCallback<List<FileOperation>> cb) {

		val qry = params.node.selectAllLinks

		qry.catchExceptions[er|cb.onFailure(er.exception)]

		qry.get [ children |
			
			Async.forEach(children.links,
				[ link, itmcb |
					link.catchExceptions[itmcb.onFailure(exception)]
					link.catchUndefined [itmcb.onSuccess(new Value<Object>(link)) ]
					link.get [ itmcb.onSuccess(new Value<Object>(it)) ]
				],
				cb.embed [ List<Value<Object>> values |
					
					val nodes = new ArrayList<Node>(values.size())
					
					for (value : values) {
						if (value.get instanceof Node) {
							nodes.add(value.get as Node)
						} else {
							params.notifications.onInsufficientAuthorization(params.folder, value.get as Link)
						}
					} 
					
					val Iterable<Node> remotelyAdded = nodes.determineRemotelyAddedNodes
					val remotelyRemoved = nodes.determineRemotelyRemovedNodes
					val remotelyUpdated = nodes.determineRemotelyUpdatedNodes
					val agg = Async.collect(3,
						Async.embed(cb,
							[ res |
								cb.onSuccess(CollectionsUtils.flatten(res))
							]))
							
							
					remotelyAdded.deduceCreateOperations(agg.createCallback)
					remotelyRemoved.deduceRemoveOperations(agg.createCallback)
					remotelyUpdated.deduceUpdateOperations(agg.createCallback)
				])
		]

	}

	def deduceUpdateOperations(Iterable<Node> remotelyUpdated, ValueCallback<List<FileOperation>> cb) {

		val agg = Async.collect(remotelyUpdated.size,
			Async.embed(cb,
				[ res |
					cb.onSuccess(CollectionsUtils.flatten(res))
				]))

		for (updatedNode : remotelyUpdated) {

			params.converter.updateFiles(params.folder, metadata, updatedNode, agg.createCallback)

		}

	}

	def deduceCreateOperations(Iterable<Node> remotelyAdded, ValueCallback<List<FileOperation>> cb) {

		val agg = Async.collect(remotelyAdded.size,
			Async.embed(cb,
				[ res |
					cb.onSuccess(CollectionsUtils.flatten(res))
				]))

		for (newNode : remotelyAdded) {

			params.converter.createFiles(params.folder, metadata, newNode, agg.createCallback)

		}

	}

	def deduceRemoveOperations(List<ItemMetadata> remotelyRemoved, ValueCallback<List<FileOperation>> cb) {

		val agg = Async.collect(remotelyRemoved.size,
			Async.embed(cb,
				[ res |
					cb.onSuccess(CollectionsUtils.flatten(res))
				]))

		for (removedNode : remotelyRemoved) {

			params.converter.removeFiles(params.folder, metadata, removedNode, agg.createCallback)

		}

	}

	def determineRemotelyAddedNodes(List<Node> children) {

		val res = new ArrayList<Node>(0)

		for (child : children) {
			if (metadata.get(child) == null) {
				res.add(child)
			}
		}

		res
	}

	def determineRemotelyRemovedNodes(List<Node> children) {

		val res = new ArrayList<ItemMetadata>(0)
		
		val uris = new ArrayList<String>(children.size)
		for (node : children) {
			uris.add(node.uri())
		}

		for (item : metadata.children) {
			if (!uris.contains(item.uri)) {
				res.add(item)
			}

		}

		res

	}

	def determineRemotelyUpdatedNodes(List<Node> children) {
		val res = new ArrayList<Node>(children.size)

		for (node : children) {

			// TODO: not yet supported, just update all
			if (metadata.get(node) != null) {
				res.add(node)
			}

		}

		res

	}

}
