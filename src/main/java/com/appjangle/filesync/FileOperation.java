package com.appjangle.filesync;

import com.appjangle.filesync.FileOperationContext;
import de.mxro.async.Deferred;
import java.util.function.Function;

@SuppressWarnings("all")
public interface FileOperation extends Function<FileOperationContext, Deferred<Void>> {
}
