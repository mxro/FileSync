package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestHtmlNode extends CheckNodesToFilesTemplate {
	
	protected override step1_defineData() {
		val html = source.append("<html></html>", "./html")
		html.append("Html Document").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
		html.append(session.link("https://admin1.linnk.it/types/v01/isHtmlValue"))
	}
	
	protected override step2_assertFiles() {

		result.children.size => 2
		
		result.contains(".filesync-meta") => true
		
		result.contains("Html Document.html") => true
		
		result.getChild("Html Document.html").text => "<html></html>"
		
	}
	
}