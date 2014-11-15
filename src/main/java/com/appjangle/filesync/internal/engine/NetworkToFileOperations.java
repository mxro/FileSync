package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.collections.CollectionsUtils;
import io.nextweb.Node;
import io.nextweb.NodeList;
import java.util.ArrayList;
import java.util.List;

/**
 * Determines operations to be performed on local files based on remote changes made in the cloud.
 */
@SuppressWarnings("all")
public class NetworkToFileOperations {
  private final Node node;
  
  private final FileItem folder;
  
  private final Metadata metadata;
  
  private final Converter converter;
  
  public NetworkToFileOperations(final Node node, final FileItem folder, final Metadata metadata, final Converter converter) {
    this.node = node;
    this.folder = folder;
    this.metadata = metadata;
    this.converter = converter;
  }
  
  public void determineOps(final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method filter is undefined for the type NetworkToFileOperations"
      + "\ndetermineRemotelyAddedNodes cannot be resolved"
      + "\ndetermineRemotelyRemovedNodes cannot be resolved"
      + "\ndetermineRemotelyUpdatedNodes cannot be resolved"
      + "\nuri cannot be resolved"
      + "\ndeduceRemoveOperations cannot be resolved"
      + "\ndeduceUpdateOperations cannot be resolved");
  }
  
  public void deduceUpdateOperations(final Iterable<Node> remotelyUpdated, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method size is undefined for the type NetworkToFileOperations");
  }
  
  public void deduceCreateOperations(final Iterable<Node> remotelyAdded, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method size is undefined for the type NetworkToFileOperations");
  }
  
  public void deduceRemoveOperations(final List<ItemMetadata> remotelyRemoved, final ValueCallback<List<FileOperation>> cb) {
    int _size = remotelyRemoved.size();
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = Async.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = Async.<List<FileOperation>>collect(_size, _embed);
    for (final ItemMetadata removedNode : remotelyRemoved) {
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      this.converter.removeFiles(this.folder, this.metadata, removedNode, _createCallback);
    }
  }
  
  public ArrayList<Node> determineRemotelyAddedNodes(final NodeList children) {
    throw new Error("Unresolved compilation problems:"
      + "\n== cannot be resolved.");
  }
  
  public ArrayList<ItemMetadata> determineRemotelyRemovedNodes(final NodeList children) {
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved.");
  }
  
  public ArrayList<Node> determineRemotelyUpdatedNodes(final NodeList children) {
    throw new Error("Unresolved compilation problems:"
      + "\n!= cannot be resolved.");
  }
}
