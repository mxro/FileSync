package com.appjangle.filesync.tests

import com.appjangle.jre.AppjangleJre
import org.junit.Test

class TestSingleFolderSync1 {
	
	@Test
	def void test() {
		
		val server = AppjangleJre.startServer()
		
		
		val session = AppjangleJre.createSession(server)
		
		
		
		
		session.close.get
		
		server.shutdown.get
		
	}
	
}