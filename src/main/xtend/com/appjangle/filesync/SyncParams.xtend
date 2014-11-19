package com.appjangle.filesync

import de.mxro.file.FileItem
import io.nextweb.Node
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class SyncParams {
	
	var FileItem folder
	
	var Node node
	
	var SynchronizationSettings settings
	
	var SynchronizationState state
	
	var Converter converter
	
	var SyncNotifications notifications
	
	/**
	 * The Node defined as the root of the synchronzation. 
	 */
	var Node syncRoot;
	
	new() {
		
	}
	
	new(SyncParams params) {
		folder = params.folder
		node = params.node
		settings = params.settings
		state = params.state
		converter = params.converter
		notifications = params.notifications
	} 
	
}