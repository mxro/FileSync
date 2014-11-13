package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.FileToNodes;
import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements FileToNodes {
  public void update(final FileItem source, final Node node, final ValueCallback<List<Closure<NetworkOperationContext>>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThis expression is not allowed in this context, since it doesn\'t cause any side effects.");
  }
}
