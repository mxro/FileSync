package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.Converter
import com.appjangle.filesync.SynchronizationSettings
import com.appjangle.filesync.SynchronizationState
import de.mxro.file.FileItem
import io.nextweb.Node

interface SyncParams {
	
	def FileItem folder()
	
	def Node node()
	
	def SynchronizationSettings settings()
	
	def SynchronizationState state()
	
	def Converter converter()
	
}