package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.List;

@SuppressWarnings("all")
public class NetworkUtils {
  public void execute(final List<NetworkOperation> ops, final Node onNode, final ValueCallback<Success> cb) {
    final NetworkOperationContext ctx = new NetworkOperationContext() {
      public Session session() {
        return onNode.session();
      }
      
      public Node parent() {
        return onNode;
      }
    };
    for (final NetworkOperation op : ops) {
      {
        final List<Deferred<?>> qries = op.apply(ctx);
        int _size = qries.size();
        final Closure<List<Success>> _function = new Closure<List<Success>>() {
          public void apply(final List<Success> it) {
            cb.onSuccess(Success.INSTANCE);
          }
        };
        ValueCallback<List<Success>> _embed = Async.<List<Success>>embed(cb, _function);
        final Aggregator<Success> cbs = Async.<Success>collect(_size, _embed);
        for (final Deferred<?> qry : qries) {
          {
            final ValueCallback<Success> itmcb = cbs.createCallback();
            final NextwebPromise<Object> safeQry = ((NextwebPromise<Object>) qry);
            Session _session = onNode.session();
            final NextwebPromise<Object> res = _session.<Object>promise(safeQry);
            final ExceptionListener _function_1 = new ExceptionListener() {
              public void onFailure(final ExceptionResult er) {
                Throwable _exception = er.exception();
                itmcb.onFailure(_exception);
              }
            };
            res.catchExceptions(_function_1);
            final Object _function_2 = new Object() {
            };
            res.get(_function_2);
          }
        }
      }
    }
  }
}
