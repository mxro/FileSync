package com.appjangle.filesync

import de.mxro.file.FileItem
import io.nextweb.Link
import io.nextweb.Node
import java.util.List
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
	 * <p>The Node defined as the root of the synchronization.
	 * <p>All nodes under this root will be resolved. 
	 */
	var List<Link> syncRoots;
	
	new() {
		
	}
	
	new(SyncParams params) {
		folder = params.folder
		node = params.node
		settings = params.settings
		state = params.state
		converter = params.converter
		notifications = params.notifications
		syncRoots = params.syncRoots
	} 
	
}