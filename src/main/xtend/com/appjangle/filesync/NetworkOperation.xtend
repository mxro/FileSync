package com.appjangle.filesync

import delight.async.callbacks.ValueCallback
import delight.functional.Closure2
import io.nextweb.promise.NextwebOperation
import java.util.List

interface NetworkOperation extends Closure2<NetworkOperationContext, ValueCallback<List<NextwebOperation<?>>>> {
	
	
	
	
}