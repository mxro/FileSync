package com.appjangle.filesync.tests

import com.appjangle.filesync.internal.engine.N
import de.mxro.async.jre.AsyncJre
import de.mxro.fn.Success
import de.oehme.xtend.junit.JUnit

@JUnit
class TestRemoveFolder extends CheckUpdatesTemplate {
	
	override protected step1_defineData() {
		val file1 = source.append("<p>file1</p>", "./file1")
		file1.append("html1").append(session.LABEL)
		file1.append(session.HTML_VALUE)
		
		val file2 = source.append("file2", "./file2")
		file2.append("html2").append(session.LABEL)
		file2.append(session.HTML_VALUE)
		
		val file3 =source.append("file3", "./file3")
		file3.append("html3").append(session.LABEL)
		file3.append(session.HTML_VALUE)
	}
	
	override protected step2_assertFiles() {
		result.children.size => 4
		result.getChild("html1").exists => true
	}
	
	override protected step3_updateNodes() {
		source.remove(source.select("./file1"))
	}
	
	override protected step4_assertFilesAfterUpdate() {
		result.getChild("html1").exists => false
	}
	
	override protected step5_updateFiles() {
		result.deleteFile("file2")
	}
	
	override protected step6_assertNodesAfterUpdate() {
		AsyncJre.waitFor([cb |
			val qry = source.select("./file2")
			
			qry.catchExceptions(er|cb.onFailure(er.exception))
			
			qry.catchUndefined([cb.onSuccess(Success.INSTANCE)])
			
			qry.get([ cb.onFailure(new Exception("Node should have been removed.")) ])

		])
	}
	
	extension N n = new N()
	
}