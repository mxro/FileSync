package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.List;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class NetworkUtils {
  public NextwebPromise<Success> execute(final List<NetworkOperation> ops, final Node onNode, final ValueCallback<Success> cb) {
    NextwebPromise<Success> _xblockexpression = null;
    {
      final NetworkOperationContext ctx = new NetworkOperationContext() {
        public Session session() {
          return onNode.session();
        }
        
        public Node parent() {
          return onNode;
        }
      };
      int _size = ops.size();
      final Closure<List<Success>> _function = new Closure<List<Success>>() {
        public void apply(final List<Success> it) {
          cb.onSuccess(Success.INSTANCE);
        }
      };
      ValueCallback<List<Success>> _embed = Async.<List<Success>>embed(cb, _function);
      final Aggregator<Success> opscbs = Async.<Success>collect(_size, _embed);
      for (final NetworkOperation op : ops) {
        final Closure<List<Deferred<?>>> _function_1 = new Closure<List<Deferred<?>>>() {
          public void apply(final List<Deferred<?>> qries) {
            InputOutput.<String>println(("exec " + qries));
            final ValueCallback<Success> opscbsitem = opscbs.createCallback();
            int _size = qries.size();
            final Closure<List<Success>> _function = new Closure<List<Success>>() {
              public void apply(final List<Success> it) {
                opscbsitem.onSuccess(Success.INSTANCE);
              }
            };
            ValueCallback<List<Success>> _embed = Async.<List<Success>>embed(cb, _function);
            final Aggregator<Success> cbs = Async.<Success>collect(_size, _embed);
            for (final Deferred<?> qry : qries) {
              {
                final ValueCallback<Success> itmcb = cbs.createCallback();
                if ((qry instanceof Query)) {
                  final Query safeQry = ((Query) qry);
                  Session _session = onNode.session();
                  final NextwebPromise<Node> res = _session.<Node>promise(safeQry);
                  final ExceptionListener _function_1 = new ExceptionListener() {
                    public void onFailure(final ExceptionResult er) {
                      Throwable _exception = er.exception();
                      itmcb.onFailure(_exception);
                    }
                  };
                  res.catchExceptions(_function_1);
                  final Closure<Node> _function_2 = new Closure<Node>() {
                    public void apply(final Node succ) {
                      itmcb.onSuccess(Success.INSTANCE);
                    }
                  };
                  res.get(_function_2);
                } else {
                  final NextwebPromise<Object> safeQry_1 = ((NextwebPromise<Object>) qry);
                  Session _session_1 = onNode.session();
                  final NextwebPromise<Object> res_1 = _session_1.<Object>promise(safeQry_1);
                  final ExceptionListener _function_3 = new ExceptionListener() {
                    public void onFailure(final ExceptionResult er) {
                      Throwable _exception = er.exception();
                      itmcb.onFailure(_exception);
                    }
                  };
                  res_1.catchExceptions(_function_3);
                  final Closure<Object> _function_4 = new Closure<Object>() {
                    public void apply(final Object succ) {
                      itmcb.onSuccess(Success.INSTANCE);
                    }
                  };
                  res_1.get(_function_4);
                }
              }
            }
          }
        };
        ValueCallback<List<Deferred<?>>> _embed_1 = Async.<List<Deferred<?>>>embed(cb, _function_1);
        op.apply(ctx, _embed_1);
      }
      Session _session = onNode.session();
      _xblockexpression = _session.commit();
    }
    return _xblockexpression;
  }
}
