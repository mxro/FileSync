package com.appjangle.filesync

import de.mxro.fn.Closure2
import delight.async.callbacks.ValueCallback
import io.nextweb.promise.NextwebOperation
import java.util.List

interface NetworkOperation extends Closure2<NetworkOperationContext, ValueCallback<List<NextwebOperation<?>>>> {
	
	
	
	
}