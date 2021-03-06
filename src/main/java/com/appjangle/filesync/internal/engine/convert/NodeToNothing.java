package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.api.Node;
import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.file.FileItem;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure2;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class NodeToNothing implements Converter {
  private final Closure2<Node, ValueCallback<Boolean>> test;
  
  public NodeToNothing(final Closure2<Node, ValueCallback<Boolean>> test) {
    this.test = test;
  }
  
  @Override
  public boolean worksOn(final FileItem source) {
    return false;
  }
  
  @Override
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    this.test.apply(node, cb);
  }
  
  @Override
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  @Override
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  @Override
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  @Override
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ArrayList<FileOperation> _newArrayList = CollectionLiterals.<FileOperation>newArrayList();
    cb.onSuccess(_newArrayList);
  }
  
  @Override
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
  
  @Override
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new IllegalStateException("This operation should never be triggered for this converter.");
  }
}
