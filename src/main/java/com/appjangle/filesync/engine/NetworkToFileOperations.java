package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.engine.metadata.ItemMetadata;
import com.appjangle.filesync.engine.metadata.Metadata;
import com.google.common.base.Objects;
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
import org.eclipse.xtext.xbase.lib.IterableExtensions;

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
      + "\nmissing \')\' at \'{\'");
  }
  
  public void deduceUpdateOperations(final Iterable<Node> remotelyUpdated, final ValueCallback<List<FileOperation>> cb) {
    int _size = IterableExtensions.size(remotelyUpdated);
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = Async.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = Async.<List<FileOperation>>collect(_size, _embed);
    for (final Node updatedNode : remotelyUpdated) {
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      this.converter.updateFiles(this.folder, this.metadata, updatedNode, _createCallback);
    }
  }
  
  public void deduceCreateOperations(final Iterable<Node> remotelyAdded, final ValueCallback<List<FileOperation>> cb) {
    int _size = IterableExtensions.size(remotelyAdded);
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      public void apply(final List<List<FileOperation>> res) {
        List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<FileOperation>>> _embed = Async.<List<List<FileOperation>>>embed(cb, _function);
    final Aggregator<List<FileOperation>> agg = Async.<List<FileOperation>>collect(_size, _embed);
    for (final Node newNode : remotelyAdded) {
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      this.converter.createFiles(this.folder, this.metadata, newNode, _createCallback);
    }
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
    ArrayList<Node> _xblockexpression = null;
    {
      final ArrayList<Node> res = new ArrayList<Node>(0);
      for (final Node child : children) {
        ItemMetadata _get = this.metadata.get(child);
        boolean _equals = Objects.equal(_get, null);
        if (_equals) {
          res.add(child);
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public ArrayList<ItemMetadata> determineRemotelyRemovedNodes(final NodeList children) {
    ArrayList<ItemMetadata> _xblockexpression = null;
    {
      final ArrayList<ItemMetadata> res = new ArrayList<ItemMetadata>(0);
      List<ItemMetadata> _children = this.metadata.getChildren();
      for (final ItemMetadata item : _children) {
        List<String> _uris = children.uris();
        String _uri = item.uri();
        boolean _contains = _uris.contains(_uri);
        boolean _not = (!_contains);
        if (_not) {
          res.add(item);
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public ArrayList<Node> determineRemotelyUpdatedNodes(final NodeList children) {
    ArrayList<Node> _xblockexpression = null;
    {
      final ArrayList<Node> res = new ArrayList<Node>(0);
      for (final Node node : children) {
        ItemMetadata _get = this.metadata.get(node);
        boolean _notEquals = (!Objects.equal(_get, null));
        if (_notEquals) {
          res.add(node);
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
}
