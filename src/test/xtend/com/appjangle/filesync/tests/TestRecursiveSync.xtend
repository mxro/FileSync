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
		
		val node3 = source.append("node3", "./node3")
		node3.append("child1", "./child1").append("b", "./inThere").get
		node3.append("child2").append("c")
	}
	
	override protected step2_assertFiles() {
		
		result.get("node1").exists => true
		
		result.get("node1").get("sub").exists => true
		
		result.get("node3").exists => true
		
		result.get("node3").get("child1").exists => true
		
		println(result.get("node3").get("child1").children)
		
		result.get("node3").get("child1").get("inThere").exists => true
		
		result.get("node3").get("child1").get("inThere").isDirectory => true
	}
	
}