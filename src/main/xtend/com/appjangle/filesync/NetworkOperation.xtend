package com.appjangle.filesync

import de.mxro.fn.Function
import io.nextweb.promise.NextwebPromise
import java.util.List

interface NetworkOperation extends Function<NetworkOperationContext, List<NextwebPromise<?>>> {
	
	
	
	
}