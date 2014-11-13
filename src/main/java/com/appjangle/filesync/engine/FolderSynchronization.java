package com.appjangle.filesync.engine;

import io.nextweb.Node;
import java.io.File;

@SuppressWarnings("all")
public interface FolderSynchronization {
  public abstract void nodeToFolder(final Node node, final File file);
}
