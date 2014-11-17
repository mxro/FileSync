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
		
		result.get("node1").exists => true
		
		println(result.get("node1").children)
		
		result.get("node1").get("sub").exists => true
		
		result.get("child1").get("b").exists => true
		
		result.get("child2").get('b').isDirectory => true
	}
	
}