package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestCreateNode extends CheckFilesToNodesTemplate {
	
	override protected step1_defineFiles() {
		source.assertFolder('Oh my test')
	}
	
	override protected step2_assertNodes() {
		println(source.children)
		println(result.selectAll().get)
		
	}
	
	
	
}