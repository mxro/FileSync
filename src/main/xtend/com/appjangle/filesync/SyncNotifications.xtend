package com.appjangle.filesync

import de.mxro.file.FileItem
import io.nextweb.Link
import io.nextweb.Node
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class SyncNotifications {
	
	val printToStdOut = false
	
	def void onInsufficientAuthorization(FileItem inFolder, Link forNode) {
		if (printToStdOut) {
			println("Insufficient authorization for node ["+forNode+"] in folder ["+inFolder+"]")
		}
	}
	
	def void onNodeNotDefined(Node parent, Link node) {
		
	}
	
	def void onStartSynchronizing(FileItem folder, Node node) {
		
	}
	
	def void onFinishedSynchronizing(FileItem folder, Node node) {
		
	}
	
	def void onNodeSkippedBecauseItWasAlreadySynced(FileItem folder, Node node) {
		
	}
	
	
}