package com.appjangle.filesync.internal.engine

import io.nextweb.Session

class N {
	
	def static HTML_VALUE() {
		return "https://admin1.linnk.it/types/v01/isHtmlValue"
	}
	
	def  HTML_VALUE(Session session) {
		return session.link("https://admin1.linnk.it/types/v01/isHtmlValue")
	}
	
	def static TEMPLATE() {
		return "https://u1.linnk.it/6wbnoq/Types/aTemplate"
	}
	
	def static TEXT_VALUE() {
		return "https://u1.linnk.it/6wbnoq/Types/aTextValue"
	}
	
	def static LABEL() {
		return "https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"
	}
	
	def LABEL(Session session) {
		return session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel")
	}
	
}