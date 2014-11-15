package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestHtmlNode extends CheckNodesToFilesTemplate {
	
	protected override defineData() {
		val html = source.append("<html></html>", "./html")
		html.append("Html Document").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
		html.append(session.link("https://admin1.linnk.it/types/v01/isHtmlValue"))
	}
	
	protected override assertFiles() {

		result.children.size => 2
		
		result.contains(".filesync-meta") => true
		
		println(result.children)
		
		result.contains("Html Document.html") => true
		
		result.getChild("Html Document.html").text => "<html></html>"
		
	}
	
}