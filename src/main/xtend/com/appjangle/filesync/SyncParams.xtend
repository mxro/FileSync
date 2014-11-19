package com.appjangle.filesync

import com.appjangle.filesync.Converter
import com.appjangle.filesync.SynchronizationSettings
import com.appjangle.filesync.SynchronizationState
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
	
}