package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.NetworkOperation;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.promise.NextwebPromise;
import java.util.List;

@SuppressWarnings("all")
public class NetworkUtils {
  public NextwebPromise<Success> execute(final List<NetworkOperation> ops, final Node onNode, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from ValueCallback<Object> to Callback<?>");
  }
}
