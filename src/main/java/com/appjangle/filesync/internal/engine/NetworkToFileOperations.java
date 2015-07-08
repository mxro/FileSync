package com.appjangle.filesync.internal.engine;

import com.appjangle.api.Node;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.google.common.base.Objects;
import delight.async.callbacks.ValueCallback;
import java.util.ArrayList;
import java.util.List;

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
  
  public void determineOps(final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field node is not visible"
      + "\nThe field notifications is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field notifications is not visible"
      + "\nThe field node is not visible");
  }
  
  public void deduceUpdateOperations(final Iterable<Node> remotelyUpdated, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible"
      + "\nThe field folder is not visible");
  }
  
  public void deduceCreateOperations(final Iterable<Node> remotelyAdded, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible"
      + "\nThe field folder is not visible");
  }
  
  public void deduceRemoveOperations(final List<ItemMetadata> remotelyRemoved, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible"
      + "\nThe field folder is not visible");
  }
  
  public ArrayList<Node> determineRemotelyAddedNodes(final List<Node> children) {
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
  
  public ArrayList<ItemMetadata> determineRemotelyRemovedNodes(final List<Node> children) {
    ArrayList<ItemMetadata> _xblockexpression = null;
    {
      final ArrayList<ItemMetadata> res = new ArrayList<ItemMetadata>(0);
      int _size = children.size();
      final ArrayList<String> uris = new ArrayList<String>(_size);
      for (final Node node : children) {
        String _uri = node.uri();
        uris.add(_uri);
      }
      List<ItemMetadata> _children = this.metadata.getChildren();
      for (final ItemMetadata item : _children) {
        String _uri_1 = item.uri();
        boolean _contains = uris.contains(_uri_1);
        boolean _not = (!_contains);
        if (_not) {
          res.add(item);
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public ArrayList<Node> determineRemotelyUpdatedNodes(final List<Node> children) {
    ArrayList<Node> _xblockexpression = null;
    {
      int _size = children.size();
      final ArrayList<Node> res = new ArrayList<Node>(_size);
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
