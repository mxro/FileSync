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
	
	def TEMPLATE(Session session) {
		return session.link(TEMPLATE)
	}
	
	def static TEXT_VALUE() {
		return "https://u1.linnk.it/6wbnoq/Types/aTextValue"
	}
	
	def static LABEL() {
		return "https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"
	}
	
	def LABEL(Session session) {
		return session.link(LABEL)
	}
	
	def static ICON() {
		return "https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/icon32"
	}
	
	def ICON(Session session) {
		return session.link(ICON)
	}
	
	def static COFFEESCRIPT() {
		return "http://slicnet.com/mxrogm/mxrogm/data/stream/2014/1/10/n3"
	}
	
	def COFFEESCRIPT(Session session) {
		return session.link(COFFEESCRIPT)
	}
	
}