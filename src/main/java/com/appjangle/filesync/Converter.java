package com.appjangle.filesync;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.metadata.ItemMetadata;
import com.appjangle.filesync.internal.engine.metadata.Metadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Converter {
  public abstract boolean worksOn(final FileItem source);
  
  public abstract void worksOn(final Node node, final ValueCallback<Boolean> cb);
  
  public abstract void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb);
  
  public abstract void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb);
  
  public abstract void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb);
  
  public abstract void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb);
}
