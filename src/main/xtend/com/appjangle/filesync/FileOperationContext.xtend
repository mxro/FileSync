package com.appjangle.filesync

import com.appjangle.filesync.engine.metadata.Metadata
import de.mxro.file.FileItem

interface FileOperationContext {
	def FileItem folder()	
	def Metadata metadata()
}