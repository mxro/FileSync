package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements Convert {
  public String fileNameToAddress(final String fileName) {
    return fileName;
  }
  
  public void update(final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
    final String content = source.getText();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
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
  
  public void createNodes(final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
  }
  
  public void deleteNodes(final FileItemMetaData cachedFile, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
  }
}
