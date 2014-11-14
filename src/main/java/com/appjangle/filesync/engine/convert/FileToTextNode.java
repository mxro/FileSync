package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Success;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.NextwebPromise;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  public void createNodes(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
  }
  
  public void update(final NodesMetadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final String content = source.getText();
    String _name = source.getName();
    FileItemMetadata _child = metadata.getChild(_name);
    final String address = _child.uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<Deferred<?>> apply(final NetworkOperationContext ctx) {
        Session _session = ctx.session();
        Link _link = _session.link(address);
        Query _setValueSafe = _link.setValueSafe(content);
        return CollectionLiterals.<Deferred<?>>newArrayList(_setValueSafe);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void deleteNodes(final NodesMetadata metadata, final FileItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    final String address = cachedFile.uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<Deferred<?>> apply(final NetworkOperationContext ctx) {
        Node _parent = ctx.parent();
        Session _session = ctx.session();
        Link _link = _session.link(address);
        NextwebPromise<Success> _removeSafe = _parent.removeSafe(_link);
        return CollectionLiterals.<Deferred<?>>newArrayList(_removeSafe);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      final String name = source.getName();
      boolean _or = false;
      boolean _endsWith = name.endsWith(".txt");
      if (_endsWith) {
        _or = true;
      } else {
        boolean _endsWith_1 = name.endsWith(".xml");
        _or = _endsWith_1;
      }
      _xblockexpression = _or;
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    Object _value = node.value();
    cb.onSuccess(Boolean.valueOf((_value instanceof String)));
  }
}
