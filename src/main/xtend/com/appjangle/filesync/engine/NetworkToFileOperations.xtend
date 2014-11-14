package com.appjangle.filesync.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

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
		
		qry.catchExceptions(er | cb.onFailure(er.exception))
		
		qry.get 
		
		
	}
	
}