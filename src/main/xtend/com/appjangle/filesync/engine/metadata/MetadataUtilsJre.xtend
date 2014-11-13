package com.appjangle.filesync.engine.metadata

import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem
import de.mxro.file.Jre.JreFiles

class MetadataUtilsJre {

	def static NodesMetadata readFromFile(FileItem file) {

		if (!file.exists) {
			val NodesMetadata newNodesMetadata = new NodesMetadataData
			
			
			file.create
			
			
			return newNodesMetadata
			
			} 

		val xstream = new XStream

		val NodesMetadata nodesMetadata = xstream.fromXML(JreFiles.getInputStream(file)) as NodesMetadata

		nodesMetadata

	}

}
