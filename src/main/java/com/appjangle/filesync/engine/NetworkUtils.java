package com.appjangle.filesync.engine;

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
    for (final NetworkOperation op : ops) {
      {
        final List<Deferred<?>> qries = op.apply(new NetworkOperationContext() {
          public Session session() {
            return onNode.session();
          }
          
          public Node parent() {
            return onNode;
          }
        });
        int _size = qries.size();
        final Closure<List<Object>> _function = new Closure<List<Object>>() {
          public void apply(final List<Object> it) {
          }
        };
        ValueCallback<List<Object>> _embed = Async.<List<Object>>embed(cb, _function);
        final Aggregator<Object> cbs = Async.<Object>collect(_size, _embed);
        for (final Deferred<?> qry : qries) {
          {
            final ValueCallback<Object> itmcb = cbs.createCallback();
            Session _session = onNode.session();
            final NextwebPromise<?> res = _session.promise(qry);
            final ExceptionListener _function_1 = new ExceptionListener() {
              public void onFailure(final ExceptionResult er) {
                Throwable _exception = er.exception();
                itmcb.onFailure(_exception);
              }
            };
            res.catchExceptions(_function_1);
          }
        }
      }
    }
  }
}
