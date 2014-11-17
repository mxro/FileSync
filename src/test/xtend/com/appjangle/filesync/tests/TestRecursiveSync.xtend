package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestRecursiveSync extends CheckNodesToFilesTemplate{
	
	override protected doRecursiveSync() {
		true
	}
	
	override protected step1_defineData() {
		source.append("oh my").append("And in the subfolder")
		source.append('Hello').append("Any another foder")
		
		val node3 = source.append("node3")
		node3.append("child1").append("b")
		node3.append("child2").append("c")
	}
	
	override protected step2_assertFiles() {
		result.getChild("oh_my").exists => true
	}
	
}