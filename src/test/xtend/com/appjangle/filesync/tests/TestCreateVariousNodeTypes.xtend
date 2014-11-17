package com.appjangle.filesync.tests

class TestCreateVariousNodeTypes {
	
	override protected step1_defineFiles() {
		source.createFile("My Document.html").text = "<html></html>"
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