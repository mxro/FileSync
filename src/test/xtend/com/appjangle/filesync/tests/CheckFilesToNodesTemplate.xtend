package com.appjangle.filesync.tests

import com.appjangle.api.Client
import com.appjangle.api.Node
import com.appjangle.api.common.LocalServer
import com.appjangle.api.servers.Servers
import com.appjangle.filesync.FileSync
import com.appjangle.jre.Clients
import de.mxro.file.FileItem
import de.mxro.file.Jre.FilesJre
import delight.async.jre.Async
import java.io.File
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

abstract class CheckFilesToNodesTemplate {
	
	protected LocalServer server
	protected Client session
	protected Node result
	protected File sourceFolder
	protected FileItem source
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Before
	def void setUp() {
		server = Servers.startServer()

		session = Clients.createSession(server)

		result = session.seed(server).get
		
		sourceFolder = tempFolder.newFolder("sync1")
		
		source = FilesJre.wrap(sourceFolder)
	}
	
	def protected abstract void step1_defineFiles()
	
	def protected abstract void step2_assertNodes()
	
	
	@Test
	def void test() {
		Async.waitFor [cb |
			FileSync.syncSingleFolder(sourceFolder, result, cb)
		]
		
		step1_defineFiles
		
		Async.waitFor [cb |
			FileSync.syncSingleFolder(sourceFolder, result, cb)
		]
		
		
		session.commit.get
				
		step2_assertNodes
		
	}
	
	
	@After
	def void tearDown() {
		session.close.get

		server.shutdown.get
	}
}