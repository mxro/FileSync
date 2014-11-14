package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
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
public class FolderToNode implements Converter {
  public boolean worksOn(final FileItem source) {
    return source.isDirectory();
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    cb.onSuccess(Boolean.valueOf(true));
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    String _name = source.getName();
    final String simpleName = MxroGWTUtils.getSimpleName(_name);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<Deferred<?>> apply(final NetworkOperationContext ctx) {
        ArrayList<Deferred<?>> _xblockexpression = null;
        {
          Node _parent = ctx.parent();
          String _name = source.getName();
          final Query baseNode = _parent.appendSafe(_name, ("./" + simpleName));
          _xblockexpression = CollectionLiterals.<Deferred<?>>newArrayList(baseNode);
        }
        return _xblockexpression;
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    ArrayList<NetworkOperation> _newArrayList = CollectionLiterals.<NetworkOperation>newArrayList();
    cb.onSuccess(_newArrayList);
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
      public void apply(final String folderName) {
        final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
        final FileOperation _function = new FileOperation() {
          public void apply(final FileOperationContext ctx) {
            FileItem _folder = ctx.folder();
            final FileItem file = _folder.assertFolder(folderName);
            String _value = source.<String>value(String.class);
            file.setText(_value);
            Metadata _metadata = ctx.metadata();
            _metadata.add(
              new ItemMetadata() {
                public String name() {
                  return folderName;
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
                
                public String converter() {
                  Class<? extends FolderToNode> _class = FolderToNode.this.getClass();
                  return _class.toString();
                }
              });
          }
        };
        ops.add(_function);
        cb.onSuccess(ops);
      }
    };
    ValueCallback<String> _embed = Async.<String>embed(cb, _function);
    this.utils.getFileName(source, _embed);
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Extension
  private ConvertUtils utils = new ConvertUtils();
}
