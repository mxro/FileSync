package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import com.appjangle.jre.AppjangleJre
import de.mxro.async.jre.AsyncJre
import de.mxro.file.Jre.FilesJre
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class TestSingleFolderSync1 {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	def void test() {

		val server = AppjangleJre.startServer()

		val session = AppjangleJre.createSession(server)

		val data = session.seed(server)

		data.append("A Folder")

		session.commit.get

		val testFolder = folder.newFolder("sync1")

		AsyncJre.waitFor [cb |
			FileSync.sync(testFolder, data.get, cb)
		]

		println ( FilesJre.wrap(testFolder).children )

		session.close.get

		server.shutdown.get

	}

}
