package com.appjangle.filesync.engine.convert

import io.nextweb.Query

class ConvertUtils {
	
	
	def appendLabel(Query toNode, String label) {
		toNode.appendSafe(label).appendSafe(
						toNode.session().link('https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel'), "./label")
	}
	
}