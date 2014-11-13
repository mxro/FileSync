package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.FileToNodes;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements FileToNodes {
  public void update(final FileItem source, final Node node, final ValueCallback<List<Closure<NetworkOperationContext>>> cb) {
    final String content = source.getText();
    final LinkedList<Closure<NetworkOperationContext>> ops = new LinkedList<Closure<NetworkOperationContext>>();
    final Closure<NetworkOperationContext> _function = new Closure<NetworkOperationContext>() {
      public void apply(final NetworkOperationContext ctx) {
      }
    };
    ops.add(_function);
  }
}
