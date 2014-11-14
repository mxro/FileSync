package com.appjangle.filesync.engine

import com.appjangle.filesync.NetworkOperation
import com.appjangle.filesync.engine.metadata.Metadata
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre
import de.mxro.file.FileItem
import io.nextweb.Node
import java.util.ArrayList

class SyncFolder {
	
	def doIt(FileItem folder, Node node) {
		val metadataFolder = folder.assertFolder(".filesync-meta")
		
		metadataFolder.visible = false;

		val metadata = MetadataUtilsJre.readFromFile(metadataFolder.getChild("nodes.xml"))

		if (metadata == null) {
			return new ArrayList<NetworkOperation>(0)
		}
		
	}
	
}