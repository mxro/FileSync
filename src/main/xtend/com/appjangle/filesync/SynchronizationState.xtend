package com.appjangle.filesync

import io.nextweb.Node
import java.util.HashSet
import java.util.Set
import com.google.common.base.Preconditions

class SynchronizationState {
	
	/**
	 * All nodes already synchronized
	 */
	val Set<String> synced;
	
	def synchronized addSynced(Node n) {
		Preconditions.checkState(!synced.contains(n.uri()), "Node was already synced [%s]", n.uri())
		
		synced.add(n.uri())
	}
	
	
	def synchronized wasSynced(Node n) {
		synced.contains(n.uri())
	}
	
	new() {
		synced = new HashSet<String>
	}
	
}