package com.appjangle.filesync

import de.mxro.file.FileItem
import io.nextweb.Link

class SyncNotifications {
	
	def void onInsufficientAuthorization(FileItem inFolder, Link forNode) {
		println('insuf '+inFolder+' '+forNode)
	}
	
	def void onNodeNotDefined(Node parent, Link node) {
		
	}
	
	
}