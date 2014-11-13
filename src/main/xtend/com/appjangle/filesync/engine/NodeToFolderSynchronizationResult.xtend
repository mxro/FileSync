package com.appjangle.filesync.engine

import de.mxro.file.FileItem
import java.util.List

interface NodeToFolderSynchronizationResult {
	
	def boolean isSuccess()
	
	def Throwable exception()
	
	def List<FileItem> childFiles()
	
}