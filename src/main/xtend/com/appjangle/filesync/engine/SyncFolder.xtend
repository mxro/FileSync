package com.appjangle.filesync.engine

class SyncFolder {
	
	def doIt() {
		val metadata = folder.assertFolder(".filesync-meta")
		
		metadata.visible = false;

		nodes = MetadataUtilsJre.readFromFile(metadata.getChild("nodes.xml"))

		if (nodes == null) {
			return new ArrayList<NetworkOperation>(0)
		}
		
	}
	
}