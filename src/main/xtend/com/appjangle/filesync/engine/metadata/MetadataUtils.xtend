package com.appjangle.filesync.engine.metadata

import de.mxro.file.FileItem
import com.thoughtworks.xstream.XStream

class MetadataUtils {
	
	
	def static NodesMetadata readFromFile(FileItem file) {
		
		if (!file.exists)
		  return new NodesMetadataData
		  
		val xstream = new XStream
		
		xstream.fromXML(file.)
		  
		
	}
	
}