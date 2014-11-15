package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import de.mxro.async.jre.AsyncJre
import org.junit.Test

abstract class CheckUpdatesTemplate extends CheckNodesToFilesTemplate {
	
	protected abstract def void updateNodes()
	
	protected abstract def void assertFilesAfterUpdate()
	
	protected abstract def void updateFiles()
	
	protected abstract def void assertNodesAfterUpdate()
	
	@Test
	override test() {
		defineData
		session.commit.get

		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		assertFiles
		
		updateNodes
		
		session.commit.get
		
		println("SECOND SYNCCCCCCC")
		
		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		assertFilesAfterUpdate
		
		updateFiles
		
		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		assertNodesAfterUpdate
	}
	
}