package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.SyncParams
import com.appjangle.filesync.internal.engine.convert.ConvertUtils
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.collections.CollectionsUtils
import io.nextweb.Node
import io.nextweb.NodeList
import java.util.ArrayList
import java.util.List

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

		val qry = params.node.selectAll

		qry.catchExceptions[er|cb.onFailure(er.exception)]

		qry.get [ children |
			var Iterable<Node> remotelyAdded = children.determineRemotelyAddedNodes
			val remotelyRemoved = children.determineRemotelyRemovedNodes
			val remotelyUpdated = children.determineRemotelyUpdatedNodes
			
			/*
			 * Don't download nodes starting with '.'
			 */ 
			/*remotelyAdded = remotelyAdded.filter [ node |
				if (ConvertUtils.getNameFromUri(node.uri()).startsWith('.')) {
					return false;
				}
				
				return true;
			]*/
			
			val agg = Async.collect(3,
				Async.embed(cb,
					[ res |
						cb.onSuccess(CollectionsUtils.flatten(res))
					]))
				
				println(remotelyAdded)
					
			remotelyAdded.deduceCreateOperations(agg.createCallback)
			remotelyRemoved.deduceRemoveOperations(agg.createCallback)
			remotelyUpdated.deduceUpdateOperations(agg.createCallback)
					
		]

	}


	def deduceUpdateOperations(Iterable<Node> remotelyUpdated, ValueCallback<List<FileOperation>> cb ) {
		
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

	def determineRemotelyAddedNodes(NodeList children) {

		val res = new ArrayList<Node>(0)

		for (child : children) {
			if (metadata.get(child) == null) {
				res.add(child)
			}
		}

		res
	}

	def determineRemotelyRemovedNodes(NodeList children) {

		val res = new ArrayList<ItemMetadata>(0)

		for (item : metadata.children) {

			if (!children.uris.contains(item.uri)) {
				res.add(item)
			}

		}

		res

	}

	def determineRemotelyUpdatedNodes(NodeList children) {
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
