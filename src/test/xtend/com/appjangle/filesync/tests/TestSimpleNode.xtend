package com.appjangle.filesync.tests

import de.mxro.file.Jre.FilesJre
import de.oehme.xtend.junit.JUnit

@JUnit
class TestSimpleNode extends CheckNodesToFilesTemplate {

	protected override defineData() {
		source.append("A Folder")
	}
	
	protected override assertFiles() {
		val folder = FilesJre.wrap(target)
		val children = folder.children
		
		children.size => 2
		
		folder.contains(".filesync-meta") => true
		
		children.toString.contains("Folder") => true
		
	}

}
