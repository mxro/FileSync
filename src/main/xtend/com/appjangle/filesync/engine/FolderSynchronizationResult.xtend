package com.appjangle.filesync.engine

import de.mxro.file.FileItem
import java.util.List

interface FolderSynchronizationResult {
	
	def boolean isSuccess()
	
	def Throwable exception()
	
	def List<FileItem> childFiles()
	
}