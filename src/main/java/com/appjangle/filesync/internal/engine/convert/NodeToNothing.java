package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure2;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class NodeToNothing implements Converter {
  private final /* Closure2<Node, ValueCallback<Boolean>> */Object test;
  
  public NodeToNothing(final /* Closure2<Node, ValueCallback<Boolean>> */Object test) {
    this.test = test;
  }
  
  public boolean worksOn(final FileItem source) {
    return false;
  }
  
  public void worksOn(final Node node, final /* ValueCallback<Boolean> */Object cb) {
    this.test.apply(node, cb);
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  public void update(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
}
