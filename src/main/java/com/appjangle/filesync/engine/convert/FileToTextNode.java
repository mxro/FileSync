package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements Convert {
  public void update(final FileItem source, final Node node, final ValueCallback<List<Closure<NetworkOperationContext>>> cb) {
    final String content = source.getText();
    final LinkedList<Closure<NetworkOperationContext>> ops = new LinkedList<Closure<NetworkOperationContext>>();
    final Closure<NetworkOperationContext> _function = new Closure<NetworkOperationContext>() {
      public void apply(final NetworkOperationContext ctx) {
        Node _node = ctx.node();
        Query _setValueSafe = _node.setValueSafe(content);
        final ExceptionListener _function = new ExceptionListener() {
          public void onFailure(final ExceptionResult er) {
            Throwable _exception = er.exception();
            cb.onFailure(_exception);
          }
        };
        _setValueSafe.catchExceptions(_function);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
}
