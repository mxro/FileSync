package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface FileToNodes {
  public abstract void update(final FileItem source, final Node node, final ValueCallback<List<Closure<NetworkOperationContext>>> cb);
}
