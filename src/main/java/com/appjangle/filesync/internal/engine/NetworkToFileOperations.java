package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.google.common.base.Objects;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.collections.CollectionsUtils;
import io.nextweb.ListQuery;
import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.InputOutput;
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
  
  public void determineOps(final ValueCallback<List<FileOperation>> cb) {
    Node _node = this.params.getNode();
    final ListQuery qry = _node.selectAll();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<NodeList> _function_1 = new Closure<NodeList>() {
      public void apply(final NodeList children) {
        InputOutput.<NodeList>println(children);
        Iterable<Node> remotelyAdded = NetworkToFileOperations.this.determineRemotelyAddedNodes(children);
        final ArrayList<ItemMetadata> remotelyRemoved = NetworkToFileOperations.this.determineRemotelyRemovedNodes(children);
        final ArrayList<Node> remotelyUpdated = NetworkToFileOperations.this.determineRemotelyUpdatedNodes(children);
        final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
          public void apply(final List<List<FileOperation>> res) {
            List<FileOperation> _flatten = CollectionsUtils.<FileOperation>flatten(res);
            cb.onSuccess(_flatten);
          }
        };
        ValueCallback<List<List<FileOperation>>> _embed = Async.<List<List<FileOperation>>>embed(cb, _function);
        final Aggregator<List<FileOperation>> agg = Async.<List<FileOperation>>collect(3, _embed);
        ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
        NetworkToFileOperations.this.deduceCreateOperations(remotelyAdded, _createCallback);
        ValueCallback<List<FileOperation>> _createCallback_1 = agg.createCallback();
        NetworkToFileOperations.this.deduceRemoveOperations(remotelyRemoved, _createCallback_1);
        ValueCallback<List<FileOperation>> _createCallback_2 = agg.createCallback();
        NetworkToFileOperations.this.deduceUpdateOperations(remotelyUpdated, _createCallback_2);
      }
    };
    qry.get(_function_1);
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
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.updateFiles(_folder, this.metadata, updatedNode, _createCallback);
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
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.createFiles(_folder, this.metadata, newNode, _createCallback);
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
      Converter _converter = this.params.getConverter();
      FileItem _folder = this.params.getFolder();
      ValueCallback<List<FileOperation>> _createCallback = agg.createCallback();
      _converter.removeFiles(_folder, this.metadata, removedNode, _createCallback);
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
