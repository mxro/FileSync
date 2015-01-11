package com.appjangle.filesync

import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Closure2
import java.util.List
import io.nextweb.promise.NextwebOperation

interface NetworkOperation extends Closure2<NetworkOperationContext, ValueCallback<List<NextwebOperation<?>>>> {
	
	
	
	
}