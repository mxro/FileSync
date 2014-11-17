package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure2;
import io.nextweb.promise.Deferred;
import java.util.List;

@SuppressWarnings("all")
public interface NetworkOperation extends Closure2<NetworkOperationContext, ValueCallback<List<Deferred<?>>>> {
}
