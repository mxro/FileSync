package com.appjangle.filesync.internal.engine.convert

import com.appjangle.filesync.Converter
import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.NetworkOperation
import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList
import java.util.List

import static extension de.mxro.async.Async.embed

class ConverterCollection implements Converter {
	
	val List<Converter> converters;
	
	new() {
		this.converters = new ArrayList<Converter>
	}
	
	def addConverter(Converter converter) {
		this.converters.add(converter)
	}
	
	override worksOn(FileItem source) {
		
		var res = false;

		for (c: converters) {
			res = res || (c.worksOn(source))
		}

		res
	}
	
	override worksOn(Node node, ValueCallback<Boolean> cb) {
		
		val cbs = Async.collect(converters.size, cb.embed [res |
			cb.onSuccess(res.contains(true))
		])
		
		for (c:converters) {
			val itmcb = cbs.createCallback
			
			
		}
		
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