package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.FileToNodes;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements FileToNodes {
  public void update(final FileItem source, final Node node, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from (Session, Node)=>Object to NetworkOperation");
  }
}
