package com.appjangle.filesync.tests

import com.appjangle.filesync.internal.engine.N
import de.oehme.xtend.junit.Hamcrest
import de.oehme.xtend.junit.JUnit

@JUnit
@Hamcrest
class TestCreateVariousNodeTypes extends CheckFilesToNodesTemplate {
	
	override protected step1_defineFiles() {
		source.createFile("My Document.css").text = ".class {}"
	}
	
	override protected step2_assertNodes() {
		
		result.selectAll(session.CSS).get.size => equalTo(1)
		
		result.select(session.CSS).get.value() => equalTo(".class {}")
		
		
	}
	
	extension N n = new N
	
}