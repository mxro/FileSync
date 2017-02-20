package com.appjangle.filesync.internal.engine;

import com.appjangle.api.Link;
import com.appjangle.api.LinkList;
import com.appjangle.api.LinkListQuery;
import com.appjangle.api.Node;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.google.common.base.Objects;
import delight.async.AsyncCommon;
import delight.async.Value;
import delight.async.callbacks.ValueCallback;
import delight.async.helper.Aggregator;
import delight.functional.Closure;
import delight.functional.Closure2;
import delight.functional.collections.CollectionsUtils;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UnauthorizedListener;
import io.nextweb.promise.exceptions.UnauthorizedResult;
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
    final LinkListQuery qry = this.params.getNode().selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      @Override
      public void onFailure(final ExceptionResult er) {
        cb.onFailure(er.exception());
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      @Override
      public void apply(final LinkList children) {
        final Closure2<Link, ValueCallback<Value<Object>>> _function = new Closure2<Link, ValueCallback<Value<Object>>>() {
          @Override
          public void apply(final Link link, final ValueCallback<Value<Object>> itmcb) {
            final UnauthorizedListener _function = new UnauthorizedListener() {
              @Override
              public void onUnauthorized(final UnauthorizedResult it) {
                NetworkToFileOperations.this.params.getNotifications().onInsufficientAuthorization(NetworkToFileOperations.this.params.getFolder(), link);
                Value<Object> _value = new Value<Object>(link);
                itmcb.onSuccess(_value);
              }
            };
            link.catchUnauthorized(_function);
            final UndefinedListener _function_1 = new UndefinedListener() {
              @Override
              public void onUndefined(final UndefinedResult it) {
                NetworkToFileOperations.this.params.getNotifications().onNodeNotDefined(NetworkToFileOperations.this.params.getNode(), link);
                Value<Object> _value = new Value<Object>(link);
                itmcb.onSuccess(_value);
              }
            };
            link.catchUndefined(_function_1);
            final ExceptionListener _function_2 = new ExceptionListener() {
              @Override
              public void onFailure(final ExceptionResult it) {
                itmcb.onFailure(it.exception());
              }
            };
            link.catchExceptions(_function_2);
            final Closure<Node> _function_3 = new Closure<Node>() {
              @Override
              public void apply(final Node it) {
                Value<Object> _value = new Value<Object>(it);
                itmcb.onSuccess(_value);
              }
            };
            link.get(_function_3);
          }
        };
        final Closure<List<Value<Object>>> _function_1 = new Closure<List<Value<Object>>>() {
          @Override
          public void apply(final List<Value<Object>> values) {
            int _size = values.size();
            final ArrayList<Node> nodes = new ArrayList<Node>(_size);
            for (final Value<Object> value : values) {
              Object _get = value.get();
              if ((_get instanceof Node)) {
                Object _get_1 = value.get();
                nodes.add(((Node) _get_1));
              } else {
              }
            }
            final Iterable<Node> remotelyAdded = NetworkToFileOperations.this.determineRemotelyAddedNodes(nodes);
            final ArrayList<ItemMetadata> remotelyRemoved = NetworkToFileOperations.this.determineRemotelyRemovedNodes(nodes);
            final ArrayList<Node> remotelyUpdated = NetworkToFileOperations.this.determineRemotelyUpdatedNodes(nodes);
            final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
              @Override
              public void apply(final List<List<FileOperation>> res) {
                cb.onSuccess(CollectionsUtils.<FileOperation>flatten(res));
              }
            };
            final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(3, 
              AsyncCommon.<List<List<FileOperation>>>embed(cb, _function));
            NetworkToFileOperations.this.deduceCreateOperations(remotelyAdded, agg.createCallback());
            NetworkToFileOperations.this.deduceRemoveOperations(remotelyRemoved, agg.createCallback());
            NetworkToFileOperations.this.deduceUpdateOperations(remotelyUpdated, agg.createCallback());
          }
        };
        AsyncCommon.<Link, Value<Object>>forEach(children.links(), _function, 
          AsyncCommon.<List<Value<Object>>>embed(cb, _function_1));
      }
    };
    qry.get(_function_1);
  }
  
  public void deduceUpdateOperations(final Iterable<Node> remotelyUpdated, final ValueCallback<List<FileOperation>> cb) {
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        cb.onSuccess(CollectionsUtils.<FileOperation>flatten(res));
      }
    };
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(IterableExtensions.size(remotelyUpdated), 
      AsyncCommon.<List<List<FileOperation>>>embed(cb, _function));
    for (final Node updatedNode : remotelyUpdated) {
      this.params.getConverter().updateFiles(this.params.getFolder(), this.metadata, updatedNode, agg.createCallback());
    }
  }
  
  public void deduceCreateOperations(final Iterable<Node> remotelyAdded, final ValueCallback<List<FileOperation>> cb) {
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        cb.onSuccess(CollectionsUtils.<FileOperation>flatten(res));
      }
    };
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(IterableExtensions.size(remotelyAdded), 
      AsyncCommon.<List<List<FileOperation>>>embed(cb, _function));
    for (final Node newNode : remotelyAdded) {
      this.params.getConverter().createFiles(this.params.getFolder(), this.metadata, newNode, agg.createCallback());
    }
  }
  
  public void deduceRemoveOperations(final List<ItemMetadata> remotelyRemoved, final ValueCallback<List<FileOperation>> cb) {
    final Closure<List<List<FileOperation>>> _function = new Closure<List<List<FileOperation>>>() {
      @Override
      public void apply(final List<List<FileOperation>> res) {
        cb.onSuccess(CollectionsUtils.<FileOperation>flatten(res));
      }
    };
    final Aggregator<List<FileOperation>> agg = AsyncCommon.<List<FileOperation>>collect(remotelyRemoved.size(), 
      AsyncCommon.<List<List<FileOperation>>>embed(cb, _function));
    for (final ItemMetadata removedNode : remotelyRemoved) {
      this.params.getConverter().removeFiles(this.params.getFolder(), this.metadata, removedNode, agg.createCallback());
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
      int _size = children.size();
      final ArrayList<String> uris = new ArrayList<String>(_size);
      for (final Node node : children) {
        uris.add(node.uri());
      }
      List<ItemMetadata> _children = this.metadata.getChildren();
      for (final ItemMetadata item : _children) {
        boolean _contains = uris.contains(item.uri());
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
