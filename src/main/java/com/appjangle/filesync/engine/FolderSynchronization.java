package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.FolderSynchronizationResult;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;

@SuppressWarnings("all")
public interface FolderSynchronization {
  public abstract void nodeToFolder(final Node node, final FileItem file, final ValueCallback<FolderSynchronizationResult> cb);
}
