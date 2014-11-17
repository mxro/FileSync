package com.appjangle.filesync.tests

class TestCreateHtmlNode extends CheckFilesToNodesTemplate {
	
	override protected step1_defineFiles() {
		source.createFile("My Document.html").text = "<html></html>"
	}
	
	override protected step2_assertNodes() {
		
	}
	
}