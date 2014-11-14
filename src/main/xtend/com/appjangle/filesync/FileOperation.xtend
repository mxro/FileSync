package com.appjangle.filesync

import de.mxro.async.Deferred
import java.util.function.Function

interface FileOperation extends Function<FileOperationContext, Deferred<Void>> {
	
}