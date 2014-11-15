package com.appjangle.filesync.tests

import de.oehme.xtend.junit.JUnit

@JUnit
class TestHtmlFileUpdate extends CheckUpdatesTemplate {
	
	override protected defineData() {
		val html = source.append("<p>Hello 1</p>", "./html")
		html.append("The Doc For.this").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
		html.append(session.link("https://admin1.linnk.it/types/v01/isHtmlValue"))
	}
	
	override protected assertFiles() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override protected updateNodes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override protected assertFilesAfterUpdate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override protected updateFiles() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override protected assertNodesAfterUpdate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	
	
}