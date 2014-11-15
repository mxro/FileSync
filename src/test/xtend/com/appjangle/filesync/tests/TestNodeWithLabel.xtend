package com.appjangle.filesync.tests

class TestNodeWithLabel extends CheckNodesToFilesTemplate {
	protected override defineData() {
		source.append("No value", "./value").append("Labelled Node").append(session.link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'))
	}
	
	protected override assertFiles() {

		result.children.size => 2
		
		result.contains(".filesync-meta") => true
		
		result.contains("Labelled Node") => true
		
	}
}