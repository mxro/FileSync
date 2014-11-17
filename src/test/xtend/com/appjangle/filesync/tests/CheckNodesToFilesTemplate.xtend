package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import com.appjangle.jre.AppjangleJre
import de.mxro.async.jre.AsyncJre
import de.mxro.file.FileItem
import de.mxro.file.Jre.FilesJre
import io.nextweb.Node
import io.nextweb.Session
import io.nextweb.common.LocalServer
import java.io.File
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

abstract class CheckNodesToFilesTemplate {

	protected LocalServer server
	protected Session session
	protected Node source
	protected File target
	protected FileItem result

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	def void setUp() {
		server = AppjangleJre.startServer()

		session = AppjangleJre.createSession(server)

		source = session.seed(server).get

		target = tempFolder.newFolder("sync1")

		result = FilesJre.wrap(target)
	}

	def protected doRecursiveSync() {
		false
	}

	def protected abstract void step1_defineData()

	def protected abstract void step2_assertFiles()

	@Test
	def void test() {
		step1_defineData
		session.commit.get

		AsyncJre.waitFor [ cb |
			if (!doRecursiveSync) {
				FileSync.syncSingleFolder(result, source, cb)
			} else {
				FileSync.sync(result, source, cb);
			}
		]

		step2_assertFiles

	}

	@After
	def void tearDown() {
		session.close.get

		server.shutdown.get
	}

}
