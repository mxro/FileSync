package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import java.util.List;

@SuppressWarnings("all")
public interface Convert {
  public abstract void createNodes(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void update(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void deleteNodes(final NodesMetadata metadata, final FileItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb);
}
