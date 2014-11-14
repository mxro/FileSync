package com.appjangle.filesync.tests

import org.junit.Test
import com.appjangle.filesync.engine.convert.ConvertUtils

class AddressesCanBeDerivedFromUris {
	
	@Test
	def void test() {
		ConvertUtils.getNameFromUri("https://myuri.com/just/for/testing/name.xml");
	}
	
}