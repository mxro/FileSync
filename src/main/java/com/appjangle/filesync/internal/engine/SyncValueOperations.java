package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;

@SuppressWarnings("all")
public class SyncValueOperations {
  public void downloadValue(final Node node, final Metadata metadata, final FileItem folder) {
    FileItem _get = folder.get("value.txt");
    boolean _exists = _get.exists();
    boolean _not = (!_exists);
    if (_not) {
      FileItem _createFile = folder.createFile("value.txt");
      Object _value = node.value();
      String _string = _value.toString();
      _createFile.setText(_string);
      return;
    }
  }
}
