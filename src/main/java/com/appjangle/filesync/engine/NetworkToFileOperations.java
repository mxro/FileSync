package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.engine.metadata.ItemMetadata;
import com.appjangle.filesync.engine.metadata.Metadata;
import com.google.common.base.Objects;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.ListQuery;
import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
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
    final ListQuery qry = this.node.selectAll();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<NodeList> _function_1 = new Closure<NodeList>() {
      public void apply(final NodeList children) {
        final ArrayList<Node> remotelyAdded = NetworkToFileOperations.this.determineRemotelyAddedNodes(children);
        final ArrayList<ItemMetadata> remotelyRemoved = NetworkToFileOperations.this.determineRemotelyRemovedNodes(children);
        final ArrayList<ItemMetadata> remotelyUpdated = NetworkToFileOperations.this.determineRemotelyUpdatedNodes(children);
      }
    };
    qry.get(_function_1);
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
  
  public ArrayList<ItemMetadata> determineRemotelyUpdatedNodes(final NodeList children) {
    ArrayList<ItemMetadata> _xblockexpression = null;
    {
      final ArrayList<ItemMetadata> res = new ArrayList<ItemMetadata>(0);
      List<ItemMetadata> _children = this.metadata.getChildren();
      for (final ItemMetadata item : _children) {
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
}
