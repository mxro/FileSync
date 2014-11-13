package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.NodeToFolderSynchronizationResult;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class FolderSynchronization {
  public void nodeToFolder(final Node node, final FileItem folder, final ValueCallback<NodeToFolderSynchronizationResult> cb) {
    try {
      boolean _isDirectory = folder.isDirectory();
      boolean _not = (!_isDirectory);
      if (_not) {
        throw new Exception(("File passed and not directory. " + folder));
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
