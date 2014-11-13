package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperation;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface FileToNodes {
  public abstract void update(final FileItem source, final Node node, final ValueCallback<List<NetworkOperation>> cb);
}
