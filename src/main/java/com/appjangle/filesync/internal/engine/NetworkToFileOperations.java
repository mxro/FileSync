package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.google.common.base.Objects;
import io.nextweb.Node;
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
  
  public void determineOps(final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nValue cannot be resolved to a type."
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThe method catchUnauthorized is undefined for the type NetworkToFileOperations"
      + "\nThe method onSuccess is undefined for the type NetworkToFileOperations"
      + "\nValue cannot be resolved."
      + "\nThe method onSuccess is undefined for the type NetworkToFileOperations"
      + "\nValue cannot be resolved."
      + "\nThe method onFailure is undefined for the type NetworkToFileOperations"
      + "\nThe method onSuccess is undefined for the type NetworkToFileOperations"
      + "\nValue cannot be resolved."
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nonFailure cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nget cannot be resolved"
      + "\nget cannot be resolved"
      + "\ncollect cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\ncreateCallback cannot be resolved"
      + "\ncreateCallback cannot be resolved"
      + "\ncreateCallback cannot be resolved");
  }
  
  public void deduceUpdateOperations(final Iterable<Node> remotelyUpdated, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ncollect cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\ncreateCallback cannot be resolved");
  }
  
  public void deduceCreateOperations(final Iterable<Node> remotelyAdded, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ncollect cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\ncreateCallback cannot be resolved");
  }
  
  public void deduceRemoveOperations(final List<ItemMetadata> remotelyRemoved, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThe method or field AsyncCommon is undefined for the type NetworkToFileOperations"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ncollect cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\ncreateCallback cannot be resolved");
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
