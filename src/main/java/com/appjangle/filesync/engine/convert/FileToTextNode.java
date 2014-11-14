package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements Convert {
  public String fileNameToAddress(final String fileName) {
    return fileName;
  }
  
  public void update(final NodesMetadata metadata, final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from Query to List<Query>");
  }
  
  public void createNodes(final NodesMetadata metadata, final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
  }
  
  public void deleteNodes(final NodesMetadata metadata, final FileItemMetadata cachedFile, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from NextwebPromise<Success> to List<Query>");
  }
}
