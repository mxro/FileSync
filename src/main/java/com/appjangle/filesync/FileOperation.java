package com.appjangle.filesync;

import com.appjangle.filesync.FileOperationContext;
import java.util.function.Function;

@SuppressWarnings("all")
public interface FileOperation extends Function<FileOperationContext, Object> {
}
