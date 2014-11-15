package com.appjangle.filesync.tests

import de.mxro.file.Jre.FilesJre

class TestSimpleNode extends CheckFilesTempalte {

	override defineData() {
		source.append("A Folder")
	}
	
	override assertFiles() {
		println ( FilesJre.wrap(target).children )
	}

}
