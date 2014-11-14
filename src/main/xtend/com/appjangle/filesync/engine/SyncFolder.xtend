package com.appjangle.filesync.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.Metadata
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList

class SyncFolder {
	
	extension FileUtils fileUtils = new FileUtils()
	
	def doIt(FileItem folder, Node node) {
		val metadata = folder.assertMetadata
		
	}
	
}