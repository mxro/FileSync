package com.appjangle.filesync.tests

import com.appjangle.jre.AppjangleJre
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

		data.append("Just a node")

		session.commit.get

		session.close.get

		server.shutdown.get

	}

}
