package com.appjangle.filesync

import java.util.function.Function

interface FileOperation extends Function<FileOperationContext, Object> {
	
}