package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperationContext;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure2;
import io.nextweb.promise.DataOperation;
import java.util.List;

@SuppressWarnings("all")
public interface NetworkOperation extends Closure2<NetworkOperationContext, ValueCallback<List<DataOperation<?>>>> {
}
