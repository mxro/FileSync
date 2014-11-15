package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestHtmlFileUpdate extends CheckUpdatesTemplate {
	
	
	override protected step1_defineData() {
		val html = source.append("<p>Hello 1</p>", "./html")
		html.append("doc").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
		html.append(session.link("https://admin1.linnk.it/types/v01/isHtmlValue"))
	}
	
	override protected step2_assertFiles() {
		result.getChild("doc.html").text => "<p>Hello 1</p>"
	}
	
	override protected updateNodes() {
		source.select("./html").setValue("<p>Hello 1 and Hello 2 are an amazing team.</p>")
	}
	
	override protected assertFilesAfterUpdate() {
		result.getChild("doc.html").text => "<p>Hello 1 and Hello 2 are an amazing team.</p>"
	}
	
	override protected updateFiles() {
		result.getChild("doc.html").text = "And now for something different"
	}
	
	override protected assertNodesAfterUpdate() {
		source.select("./html").get.value() => "And now for something different"
	}
	
	
	
}