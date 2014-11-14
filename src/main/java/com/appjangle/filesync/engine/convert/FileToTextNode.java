package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.engine.convert.ConvertUtils;
import com.appjangle.filesync.engine.metadata.ItemMetadata;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.NextwebPromise;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import mx.gwtutils.MxroGWTUtils;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  @Extension
  private ConvertUtils utils = new ConvertUtils();
  
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      final String name = source.getName();
      _xblockexpression = name.endsWith(".txt");
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    Object _value = node.value();
    cb.onSuccess(Boolean.valueOf((_value instanceof String)));
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    String _name = source.getName();
    final String nameWithoutExtension = MxroGWTUtils.removeExtension(_name);
    final String simpleName = MxroGWTUtils.getSimpleName(nameWithoutExtension);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<Deferred<?>> apply(final NetworkOperationContext ctx) {
        ArrayList<Deferred<?>> _xblockexpression = null;
        {
          Node _parent = ctx.parent();
          String _text = source.getText();
          final Query baseNode = _parent.appendSafe(_text, ("./" + simpleName));
          Query _appendLabel = FileToTextNode.this.utils.appendLabel(baseNode, nameWithoutExtension);
          _xblockexpression = CollectionLiterals.<Deferred<?>>newArrayList(baseNode, _appendLabel);
        }
        return _xblockexpression;
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final String content = source.getText();
    String _name = source.getName();
    ItemMetadata _child = metadata.getChild(_name);
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
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
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
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String fileName) {
        final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
        final FileOperation _function = new FileOperation() {
          public void apply(final FileOperationContext ctx) {
            FileItem _folder = ctx.folder();
            final FileItem file = _folder.createFile(fileName);
            String _value = source.<String>value(String.class);
            file.setText(_value);
            Metadata _metadata = ctx.metadata();
            _metadata.add(new ItemMetadata() {
              public String name() {
                return fileName;
              }
              
              public Date lastModified() {
                return new Date();
              }
              
              public String uri() {
                return source.uri();
              }
              
              public String hash() {
                return file.hash();
              }
            });
          }
        };
        ops.add(_function);
        cb.onSuccess(ops);
      }
    };
    ValueCallback<String> _embed = Async.<String>embed(cb, _function);
    this.utils.getFileName(source, folder, ".txt", _embed);
  }
}
