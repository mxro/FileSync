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
      + "\nInvalid number of arguments. The method apply(P1, P2) is not applicable for the arguments (new NetworkOperationContext(){})"
      + "\nType mismatch: type void is not applicable at this location"
      + "\nsize cannot be resolved");
  }
}
