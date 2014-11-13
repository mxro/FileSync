package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Convert {
  public abstract void update(final FileItem source, final Node node, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void createNodes(final FileItem source, final Node node, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void deleteNodes(final FileItemMetaData cachedFile, final Node node, final ValueCallback<List<NetworkOperation>> cb);
}
