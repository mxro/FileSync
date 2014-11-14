package com.appjangle.filesync.engine.metadata

import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem
import de.mxro.file.Jre.JreFiles

class MetadataUtilsJre {

	def static Metadata readFromFile(FileItem file) {

		if (!file.exists) {
			
			return null
			
			} 

		val xstream = new XStream

		val Metadata nodesMetadata = xstream.fromXML(JreFiles.getInputStream(file)) as Metadata

		nodesMetadata

	}

}
