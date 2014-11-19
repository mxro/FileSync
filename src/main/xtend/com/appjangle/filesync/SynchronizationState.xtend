package com.appjangle.filesync

import io.nextweb.Node
import java.util.HashSet
import java.util.Set

class SynchronizationState {
	
	/**
	 * All nodes already synchronized
	 */
	val Set<String> synced;
	
	def addSynced(Node n) {
		
		synced.add(n.uri())
	}
	
	
	new() {
		synced = new HashSet<String>
	}
	
}