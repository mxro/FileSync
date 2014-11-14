package com.appjangle.filesync

import de.mxro.async.callbacks.ValueCallback
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.List

interface Converter {

	def boolean worksOn(FileItem source)
	
	def void worksOn(Node node, ValueCallback<Boolean> cb)
	
	def void createNodes(Metadata metadata, FileItem source, ValueCallback<List<NetworkOperation>> cb)
	
	def void update(Metadata metadata, FileItem source,  ValueCallback<List<NetworkOperation>> cb)
	
	def void deleteNodes(Metadata metadata, ItemMetadata cachedFile, ValueCallback<List<NetworkOperation>> cb)
	
	def void createFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb)
	
	def void updateFiles(FileItem folder, Metadata metadata, Node source, ValueCallback<List<FileOperation>> cb)
	
	def void removeFiles(FileItem folder, Metadata metadata, ItemMetadata item, ValueCallback<List<FileOperation>> cb)
}