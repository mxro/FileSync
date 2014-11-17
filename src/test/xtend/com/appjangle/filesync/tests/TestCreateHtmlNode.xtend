package com.appjangle.filesync.tests

import de.oehme.xtend.junit.Hamcrest
import de.oehme.xtend.junit.JUnit

@JUnit
@Hamcrest
class TestCreateHtmlNode extends CheckFilesToNodesTemplate {
	
	override protected step1_defineFiles() {
		source.createFile("My Document.html").text = "<html></html>"
	}
	
	override protected step2_assertNodes() {
		
		val node = result.select("./My_Document").get()
		
		node => notNullValue
		
		
		
		
	}
	
}