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
		result.select("./.n/My_Document").get
		
		println (result.selectAll.get)
		
		val node = result.select(session.HTML_VALUE).get
		
		node.value() => equalTo("<html></html>")
		
		node.select(node.session().LABEL).get().value() => equalTo("My Document")
	}
	
	extension N n = new N
	
}