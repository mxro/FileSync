package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import de.mxro.file.FileItem;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.async.helper.Aggregator;
import delight.functional.Closure;
import delight.functional.collections.CollectionsUtils;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Determines operations to be performed on local files based on remote changes made in the cloud.
 */
@SuppressWarnings("all")
public class NetworkToFileOperations {
  private final SyncParams params;
  
  private final Metadata metadata;
  
  public NetworkToFileOperations(final SyncParams params, final Metadata metadata) {
    this.params = params;
    this.metadata = metadata;
  }
  
  public Object determineOps(final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nNode cannot be resolved to a type."
      + "\nNode cannot be resolved to a type."
      + "\nNode cannot be resolved to a type."
      + "\nNode cannot be resolved to a type."
      + "\nThe method exception is undefined for the type NetworkToFileOperations"
      + "\nThe method links is undefined for the type NetworkToFileOperations"
      + "\nThe method catchUnauthorized is undefined for the type NetworkToFileOperations"
      + "\nThe method catchUndefined is undefined for the type NetworkToFileOperations"
      + "\nThe method catchExceptions is undefined for the type NetworkToFileOperations"
      + "\nThe method or field exception is undefined for the type NetworkToFileOperations"
      + "\nThe method get is undefined for the type NetworkToFileOperations"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nselectAllLinks cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  public void deduceUpdateOperations(final /* Iterable<Node> */Object remotelyUpdated, final ValueCallback<List<FileOperation>> cb) {
    int _size = IterableExtensions.size(remotelyUpdated);
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = AsyncCommon.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(_size, _embed);
    for (final Node updatedNode : remotelyUpdated) {
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.updateFiles(_folder, this.metadata, updatedNode, _createCallback);
    }
  }
  
  public void deduceCreateOperations(final /* Iterable<Node> */Object remotelyAdded, final ValueCallback<List<FileOperation>> cb) {
    int _size = IterableExtensions.size(remotelyAdded);
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = AsyncCommon.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(_size, _embed);
    for (final Node newNode : remotelyAdded) {
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.createFiles(_folder, this.metadata, newNode, _createCallback);
    }
  }
  
  public void deduceRemoveOperations(final List<ItemMetadata> remotelyRemoved, final ValueCallback<List<FileOperation>> cb) {
    int _size = remotelyRemoved.size();
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = AsyncCommon.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(_size, _embed);
    for (final ItemMetadata removedNode : remotelyRemoved) {
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.removeFiles(_folder, this.metadata, removedNode, _createCallback);
    }
  }
  
  public /* ArrayList<Node> */Object determineRemotelyAddedNodes(final /* List<Node> */Object children) {
    throw new Error("Unresolved compilation problems:"
      + "\nNode cannot be resolved to a type.");
  }
  
  public ArrayList<ItemMetadata> determineRemotelyRemovedNodes(final /* List<Node> */Object children) {
    throw new Error("Unresolved compilation problems:"
      + "\nuri cannot be resolved");
  }
  
  public /* ArrayList<Node> */Object determineRemotelyUpdatedNodes(final /* List<Node> */Object children) {
    throw new Error("Unresolved compilation problems:"
      + "\nNode cannot be resolved to a type.");
  }
}
