package com.appjangle.filesync.engine.metadata

import com.thoughtworks.xstream.XStream
import de.mxro.file.FileItem
import de.mxro.file.Jre.W

class MetadataUtilsJre {
	
	
	def static NodesMetadata readFromFile(FileItem file) {
		
		if (!file.exists)
		  return new NodesMetadataData
		  
		val xstream = new XStream
		
		xstream.fromXML(W.getInputStream(file))
		  
		
	}
	
}