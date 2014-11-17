package com.appjangle.filesync.tests

class TestRecursiveSync extends CheckNodesToFilesTemplate{
	
	override protected doRecursiveSync() {
		true
	}
	
	
	override protected step1_defineData() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override protected step2_assertFiles() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}