package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.async.helper.Aggregator;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.NextwebOperation;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.List;

@SuppressWarnings("all")
public class NetworkUtils {
  public NextwebPromise<Success> execute(final List<NetworkOperation> ops, final Node onNode, final ValueCallback<Success> cb) {
    NextwebPromise<Success> _xblockexpression = null;
    {
      final NetworkOperationContext ctx = new NetworkOperationContext() {
        @Override
        public Session session() {
          return onNode.session();
        }
        
        @Override
        public Node parent() {
          return onNode;
        }
      };
      int _size = ops.size();
      final Closure<List<Success>> _function = new Closure<List<Success>>() {
        @Override
        public void apply(final List<Success> it) {
          cb.onSuccess(Success.INSTANCE);
        }
      };
      ValueCallback<List<Success>> _embed = AsyncCommon.<List<Success>>embed(cb, _function);
      final Aggregator<Success> opscbs = AsyncCommon.<Success>collect(_size, _embed);
      for (final NetworkOperation op : ops) {
        final Closure<List<NextwebOperation<?>>> _function_1 = new Closure<List<NextwebOperation<?>>>() {
          @Override
          public void apply(final List<NextwebOperation<?>> qries) {
            final ValueCallback<Success> opscbsitem = opscbs.createCallback();
            int _size = qries.size();
            final Closure<List<Success>> _function = new Closure<List<Success>>() {
              @Override
              public void apply(final List<Success> it) {
                opscbsitem.onSuccess(Success.INSTANCE);
              }
            };
            ValueCallback<List<Success>> _embed = AsyncCommon.<List<Success>>embed(cb, _function);
            final Aggregator<Success> cbs = AsyncCommon.<Success>collect(_size, _embed);
            for (final NextwebOperation<?> qry : qries) {
              {
                final ValueCallback<Success> itmcb = cbs.createCallback();
                if ((qry instanceof Query)) {
                  final ExceptionListener _function_1 = new ExceptionListener() {
                    @Override
                    public void onFailure(final ExceptionResult er) {
                      Throwable _exception = er.exception();
                      itmcb.onFailure(_exception);
                    }
                  };
                  ((Query)qry).catchExceptions(_function_1);
                  final Closure<Node> _function_2 = new Closure<Node>() {
                    @Override
                    public void apply(final Node succ) {
                      itmcb.onSuccess(Success.INSTANCE);
                    }
                  };
                  ((Query)qry).get(_function_2);
                } else {
                  if ((qry instanceof NextwebPromise<?>)) {
                    final NextwebPromise<Object> safeQry = ((NextwebPromise<Object>) qry);
                    final ExceptionListener _function_3 = new ExceptionListener() {
                      @Override
                      public void onFailure(final ExceptionResult er) {
                        Throwable _exception = er.exception();
                        itmcb.onFailure(_exception);
                      }
                    };
                    safeQry.catchExceptions(_function_3);
                    final Closure<Object> _function_4 = new Closure<Object>() {
                      @Override
                      public void apply(final Object succ) {
                        itmcb.onSuccess(Success.INSTANCE);
                      }
                    };
                    safeQry.get(_function_4);
                  } else {
                    Class<? extends NextwebOperation> _class = qry.getClass();
                    String _plus = ("Unsupported pending query: " + _class);
                    throw new RuntimeException(_plus);
                  }
                }
              }
            }
          }
        };
        ValueCallback<List<NextwebOperation<?>>> _embed_1 = AsyncCommon.<List<NextwebOperation<?>>>embed(cb, _function_1);
        op.apply(ctx, _embed_1);
      }
      Session _session = onNode.session();
      _xblockexpression = _session.commit();
    }
    return _xblockexpression;
  }
}
