package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SyncParams;
import com.google.common.base.Objects;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.Value;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.collections.CollectionsUtils;
import io.nextweb.Link;
import io.nextweb.LinkList;
import io.nextweb.LinkListQuery;
import io.nextweb.Node;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
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
  
  public void determineOps(final ValueCallback<List<FileOperation>> cb) {
    Node _node = this.params.getNode();
    final LinkListQuery qry = _node.selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      public void apply(final LinkList children) {
        List<Link> _links = children.links();
        final Closure2<Link, ValueCallback<Value<Object>>> _function = new Closure2<Link, ValueCallback<Value<Object>>>() {
          public void apply(final Link link, final ValueCallback<Value<Object>> itmcb) {
            final ExceptionListener _function = new ExceptionListener() {
              public void onFailure(final ExceptionResult it) {
                Throwable _exception = it.exception();
                itmcb.onFailure(_exception);
              }
            };
            link.catchExceptions(_function);
            final UndefinedListener _function_1 = new UndefinedListener() {
              public void onUndefined(final UndefinedResult it) {
                Value<Object> _value = new Value<Object>(link);
                itmcb.onSuccess(_value);
              }
            };
            link.catchUndefined(_function_1);
            final Closure<Node> _function_2 = new Closure<Node>() {
              public void apply(final Node it) {
                Value<Object> _value = new Value<Object>(it);
                itmcb.onSuccess(_value);
              }
            };
            link.get(_function_2);
          }
        };
        final Closure<List<Value<Object>>> _function_1 = new Closure<List<Value<Object>>>() {
          public void apply(final List<Value<Object>> values) {
            int _size = values.size();
            final ArrayList<Node> nodes = new ArrayList<Node>(_size);
            for (final Value<Object> value : values) {
              Object _get = value.get();
              if ((_get instanceof Node)) {
                Object _get_1 = value.get();
                nodes.add(((Node) _get_1));
              } else {
                SyncNotifications _notifications = NetworkToFileOperations.this.params.getNotifications();
                FileItem _folder = NetworkToFileOperations.this.params.getFolder();
                Object _get_2 = value.get();
                _notifications.onInsufficientAuthorization(_folder, ((Link) _get_2));
              }
            }
            final Iterable<Node> remotelyAdded = NetworkToFileOperations.this.determineRemotelyAddedNodes(nodes);
            final ArrayList<ItemMetadata> remotelyRemoved = NetworkToFileOperations.this.determineRemotelyRemovedNodes(nodes);
            final ArrayList<Node> remotelyUpdated = NetworkToFileOperations.this.determineRemotelyUpdatedNodes(nodes);
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
        ValueCallback<List<Value<Object>>> _embed = Async.<List<Value<Object>>>embed(cb, _function_1);
        Async.<Link, Value<Object>>forEach(_links, _function, _embed);
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
      List<ItemMetadata> _children = this.metadata.getChildren();
      for (final ItemMetadata item : _children) {
        for (final Node n : children) {
          {
            boolean contains = false;
            String _uri = n.uri();
            String _uri_1 = item.uri();
            boolean _equals = Objects.equal(_uri, _uri_1);
            if (_equals) {
              contains = true;
            }
            if ((!contains)) {
              res.add(item);
            }
          }
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
