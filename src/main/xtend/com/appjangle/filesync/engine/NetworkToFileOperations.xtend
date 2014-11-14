package com.appjangle.filesync.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import io.nextweb.NodeList
import java.util.ArrayList
import java.util.List
import com.appjangle.filesync.engine.metadata.ItemMetadata

/**
 * Determines operations to be performed on local files based on remote changes made in the cloud.
 */
class NetworkToFileOperations {
	
	val Node node;
	val FileItem folder;
	val Metadata metadata;
	val Converter converter;
	
	new(Node node, FileItem folder, Metadata metadata, Converter converter) {
		this.node = node
		this.folder = folder
		this.metadata = metadata
		this.converter = converter
		
	}
	
	
	def determineOps( ValueCallback<List<FileOperation>> cb) {
		
		val qry = node.selectAll
		
		qry.catchExceptions [er | cb.onFailure(er.exception)]
		
		qry.get [children |
			
			val remotelyAdded = children.determineRemotelyAddedNodes
			
			val remotelyRemoved = children.determineRemotelyRemovedNodes
			
			val remotelyUpdated = children.determineRemotelyUpdatedNodes
			
		]
		
		
	}
	
	
	def determineRemotelyAddedNodes(NodeList children) {
		
		val res = new ArrayList<Node>(0)
		
		for (child: children) {
			if (metadata.get(child) == null) {
				res.add(child)
			}
		}
		
		res
	}
	
	def determineRemotelyRemovedNodes(NodeList children) {
		
		val res = new ArrayList<ItemMetadata>(0)
		
		for (item: metadata.children) {
			
			if (!children.uris.contains(item.uri)) {
				res.add(item)
			}
			
		}
		
		res
		
	}
	
	def determineRemotelyUpdatedNodes(NodeList children) {
		val res = new ArrayList<ItemMetadata>(0)
		
		for (item : metadata.children) {
			
			
			
		}
		
		res
		
	}
	
	
}