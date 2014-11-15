package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestHtmlFileUpdate extends CheckUpdatesTemplate {
	
	override protected defineData() {
		val html = source.append("<p>Hello 1</p>", "./html")
		html.append("doc").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
		html.append(session.link("https://admin1.linnk.it/types/v01/isHtmlValue"))
	}
	
	override protected assertFiles() {
		result.getChild("doc.html").text => "<p>Hello 1</p>"
	}
	
	override protected updateNodes() {
		source.select("./html").setValue("<p>Hello 1 and Hello 2 are an amazing team.</p>")
	}
	
	override protected assertFilesAfterUpdate() {
		result.getChild("doc.html").text => "<p>Hello 1 and Hello 2 are an amazing team.</p>"
	}
	
	override protected updateFiles() {
		
	}
	
	override protected assertNodesAfterUpdate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	
	
}