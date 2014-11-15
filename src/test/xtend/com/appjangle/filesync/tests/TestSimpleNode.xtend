package com.appjangle.filesync.tests

import de.mxro.file.Jre.FilesJre
import de.oehme.xtend.junit.JUnit

@JUnit
class TestSimpleNode extends CheckFilesTempalte {

	override defineData() {
		source.append("A Folder")
	}
	
	override assertFiles() {
		val children = FilesJre.wrap(target).children
		
		children.size => 2
		
		// children.contains(".filesync-meta") => true
		
		// children.toString.contains("Folder") => true
		
	}

}
