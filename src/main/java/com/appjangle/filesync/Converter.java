package com.appjangle.filesync;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Converter {
  public abstract boolean worksOn(final FileItem source);
  
  public abstract void worksOn(final Node node, final ValueCallback<Boolean> cb);
  
  public abstract void createNodes(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void update(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void deleteNodes(final NodesMetadata metadata, final FileItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void createFiles(final NodesMetadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb);
}
