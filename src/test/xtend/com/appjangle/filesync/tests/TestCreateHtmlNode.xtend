package com.appjangle.filesync.tests

import com.appjangle.filesync.internal.engine.N
import de.oehme.xtend.junit.Hamcrest
import de.oehme.xtend.junit.JUnit

@JUnit
@Hamcrest
class TestCreateHtmlNode extends CheckFilesToNodesTemplate {
	
	override protected step1_defineFiles() {
		source.createFile("My Document.html").text = "<html></html>"
	}
	
	override protected step2_assertNodes() {
		val node = result.select("./My_Document").get
		
		// println (node.selectAll.get)
		
		result.selectAll(session.HTML_VALUE).get.size => equalTo(1)
		
		node.value() => equalTo("<html></html>")
		
		node.select(node.session().LABEL).get().value() => equalTo("My Document")
	}
	
	extension N n = new N
	
}