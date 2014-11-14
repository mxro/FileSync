package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList
import java.util.List

class ConverterCollection implements Converter {
	
	val List<Converter> converters;
	
	new() {
		this.converters = new ArrayList<Converter>
	}
	
	def addConverter(Converter converter) {
		this.converters.add(converter)
	}
	
	override worksOn(FileItem source) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override worksOn(Node node, ValueCallback<Boolean> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override update(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override removeFiles(FileItem folder, Metadata metadata, ItemMetadata item, ValueCallback<List<FileOperation>> cb) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}