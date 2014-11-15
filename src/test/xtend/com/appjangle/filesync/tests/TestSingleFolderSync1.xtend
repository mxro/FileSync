package com.appjangle.filesync.tests

import com.appjangle.jre.AppjangleJre
import org.junit.Test

class TestSingleFolderSync1 {
	
	@Test
	def void test() {
		
		val testServer = AppjangleJre.startServer()
		
		
		
		testServer.shutdown.get
		
	}
	
}