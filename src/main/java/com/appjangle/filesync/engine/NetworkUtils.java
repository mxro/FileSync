package com.appjangle.filesync.engine;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import java.util.List;

@SuppressWarnings("all")
public class NetworkUtils {
  public void execute(final List<NetworkOperation> ops, final Node onNode, final ValueCallback<Success> cb) {
    for (final NetworkOperation op : ops) {
      final List<Deferred<?>> qries = op.apply(new NetworkOperationContext() {
        public Session session() {
          return onNode.session();
        }
        
        public Node parent() {
          return onNode;
        }
      });
    }
  }
}
