package com.appjangle.filesync.internal.engine.metadata

import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem

class MetadataUtilsJre {

	def static com.appjangle.filesync.engine.metadata.Metadata readFromFile(FileItem file) {

		if (!file.exists) {
			
			return null
			
			} 

		val xstream = new XStream
	
		val com.appjangle.filesync.engine.metadata.Metadata nodesMetadata = xstream.fromXML(file.text) as com.appjangle.filesync.engine.metadata.Metadata

		nodesMetadata

	}

}
