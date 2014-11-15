package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import com.appjangle.jre.AppjangleJre
import de.mxro.async.jre.AsyncJre
import io.nextweb.Node
import io.nextweb.Session
import io.nextweb.common.LocalServer
import java.io.File
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

abstract class CheckFilesTempalte {
	
	LocalServer server
	Session session
	Node source
	File target
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Before
	def void setUp() {
		server = AppjangleJre.startServer()

		session = AppjangleJre.createSession(server)

		source = session.seed(server).get
		
		target = tempFolder.newFolder("sync1")
	}
	
	def abstract void defineData()
	
	def abstract void assertFiles()
	
	@Test
	def void test() {
		defineData
		session.commit.get

		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		assertFiles
		
	}
	
	
	@After
	def void tearDown() {
		session.close.get

		server.shutdown.get
	}
	
}