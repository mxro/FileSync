package com.appjangle.filesync.jre

import com.appjangle.filesync.FileSync
import de.mxro.async.jre.AsyncJre
import de.mxro.file.Jre.FilesJre
import io.nextweb.Node
import java.io.File

class FileSyncJre {
	
	def static void sync(File folder, Node node) {
		AsyncJre.waitFor([cb |
			FileSync.sync(FilesJre.wrap(folder), node, cb)
		])
	}
	
}