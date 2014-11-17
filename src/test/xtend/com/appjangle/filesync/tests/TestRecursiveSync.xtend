package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestRecursiveSync extends CheckNodesToFilesTemplate{
	
	override protected doRecursiveSync() {
		true
	}
	
	override protected step1_defineData() {
		source.append("oh my", "./node1").append("And in the subfolder", "./sub")
		source.append('Hello').append("Any another foder")
		
		val node3 = source.append("node3")
		node3.append("child1").append("b")
		node3.append("child2").append("c")
	}
	
	override protected step2_assertFiles() {
		
		
		result.getChild("node1").exists => true
		
		println(result.getChild("node1").children)
		
		result.getChild("node1").getChild("sub").exists => true
		
		result.getChild("child1").getChild("b").exists => true
		
		result.getChild("child2").getChild('b').isDirectory => true
	}
	
}