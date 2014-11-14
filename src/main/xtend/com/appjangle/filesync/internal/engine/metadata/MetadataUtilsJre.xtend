package com.appjangle.filesync.internal.engine.metadata

import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem

class MetadataUtilsJre {

	def static Metadata readFromFile(FileItem file) {

		if (!file.exists) {
			
			return null
			
			} 

		val xstream = new XStream
	
		val Metadata nodesMetadata = xstream.fromXML(file.text) as Metadata

		nodesMetadata

	}

}
