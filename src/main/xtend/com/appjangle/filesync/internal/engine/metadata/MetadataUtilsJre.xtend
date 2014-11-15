package com.appjangle.filesync.internal.engine.metadata

import com.appjangle.filesync.Metadata
import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem

class MetadataUtilsJre {

	def static Metadata readFromFile(FileItem file) {

		if (!file.exists) {
			
			return null
			
			} 

		val xstream = new XStream
	
		val NodesXml nodesXml = xstream.fromXML(file.text) as NodesXml

		nodesMetadata

	}

}
