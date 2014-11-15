package com.appjangle.filesync.internal.engine.metadata

import com.appjangle.filesync.ItemMetadata
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml
import com.appjangle.filesync.internal.engine.metadata.v01.NodesXml
import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem

class MetadataUtilsJre {

	def static Metadata readFromFile(FileItem file) {

		if (!file.exists) {

			return null

		}

		val xstream = new XStream

		val NodesXml nodesXml = xstream.fromXML(file.text) as NodesXml

		println("LOAD! "+file.text)

		nodesXml.toMetadata

	}

	def static Metadata toMetadata(NodesXml nodesXml) {
		
		val metadata = new MetadataImpl
		
		for ( item:nodesXml.items) {
			metadata.add(item)
		}
		
		metadata
		
	}
	
	def static void saveToFile(Metadata metadata, FileItem file) {
		if (!file.exists) {
			throw new RuntimeException("File doesn't exist.")
		}
		
		val xstream = new XStream
		
		val nodesXml = metadata.toNodesXml
		
		file.text = xstream.toXML(nodesXml)
		
		println("Save !\n"+file.text)
		
	}

	def static NodesXml toNodesXml(Metadata metadata) {
		var nodesXml = new NodesXml
		
		for (item: metadata.children) {
			
			nodesXml.items.add(item.toItemXml)
			
		}
		
		nodesXml
		
	}
	
	def static ItemXml toItemXml(ItemMetadata item) {
		val itemXml = new ItemXml
		
		itemXml.converter = item.converter
		itemXml.lastModified = item.lastModified
		itemXml.hash = item.hash
		itemXml.name = item.name
		itemXml.uri = item.uri
		
		itemXml
	}

}
