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
		
		Async.forEach(converters, [c, itmcb |
			c.worksOn(node, itmcb)
		], cb.embed [res |
			cb.onSuccess(res.contains(true))
		])
		
	}
	
	def private findConverter(FileItem forFileItem, ValueCallback<Converter> cb) {
		Async.forEach(converters, [c, itmcb |
			if (c.worksOn(forFileItem)) {
				itmcb.onSuccess(c)
			} else {
				itmcb.onSuccess(ConvertUtils.NO_VALUE)
			}
		], cb.embed [res |
			for (item : res) {
				if (item instanceof Converter) {
					cb.onSuccess(item)
					return;
				}
			}
		])
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