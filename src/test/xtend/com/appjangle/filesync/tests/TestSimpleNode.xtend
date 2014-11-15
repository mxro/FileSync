package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestSimpleNode extends CheckNodesToFilesTemplate {

	protected override defineData() {
		source.append("A Folder")
	}
	
	protected override assertFiles() {

		result.children.size => 2
		
		result.contains(".filesync-meta") => true
		
		result.children.toString.contains("Folder") => true
		
	}

}
