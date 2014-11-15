package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import com.appjangle.jre.AppjangleJre
import de.mxro.async.jre.AsyncJre
import de.mxro.file.Jre.FilesJre
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class TestSingleFolderSync1 {

	

	@Test
	def void test() {

		

		data.append("A Folder")

		

		println ( FilesJre.wrap(testFolder).children )

		

	}

}
