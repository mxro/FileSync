package com.appjangle.filesync.tests

import com.appjangle.filesync.FileSync
import de.mxro.async.jre.AsyncJre
import org.junit.Test

abstract class CheckUpdatesTemplate extends CheckNodesToFilesTemplate {
	
	protected abstract def void step3_updateNodes()
	
	protected abstract def void step4_assertFilesAfterUpdate()
	
	protected abstract def void step5_updateFiles()
	
	protected abstract def void step6_assertNodesAfterUpdate()
	
	@Test
	override test() {
		step1_defineData
		session.commit.get

		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		step2_assertFiles
		
		step3_updateNodes
		
		session.commit.get
		
		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		step4_assertFilesAfterUpdate
		
		step5_updateFiles
		
		AsyncJre.waitFor [cb |
			FileSync.sync(target, source, cb)
		]
		
		step6_assertNodesAfterUpdate
	}
	
}