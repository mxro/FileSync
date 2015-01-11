package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.async.AsyncCommon;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.NextwebOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    final String simpleName = this.futils.getSimpleName(_name);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<NextwebOperation<?>>> opscb) {
        Node _parent = ctx.parent();
        String _name = source.getName();
        final Query baseNode = _parent.appendSafe(_name, ("./" + simpleName));
        metadata.add(
          new ItemMetadata() {
            public String name() {
              return source.getName();
            }
            
            public Date lastModified() {
              return source.lastModified();
            }
            
            public String uri() {
              Node _parent = ctx.parent();
              String _uri = _parent.uri();
              String _plus = (_uri + "/");
              return (_plus + simpleName);
            }
            
            public String hash() {
              int _hashCode = simpleName.hashCode();
              return Integer.valueOf(_hashCode).toString();
            }
            
            public String converter() {
              Class<? extends FolderToNode> _class = FolderToNode.this.getClass();
              return _class.toString();
            }
          });
        String _name_1 = source.getName();
        Query _appendSafe = baseNode.appendSafe(_name_1, "./.label");
        Session _session = baseNode.session();
        Link _LABEL = FolderToNode.this.n.LABEL(_session);
        Query _appendSafe_1 = _appendSafe.appendSafe(_LABEL);
        Query _appendSafe_2 = baseNode.appendSafe("https://appjangle.com/files/img/20141020/List.png", "./.icon");
        Session _session_1 = baseNode.session();
        Link _ICON = FolderToNode.this.n.ICON(_session_1);
        Query _appendSafe_3 = _appendSafe_2.appendSafe(_ICON);
        ArrayList<NextwebOperation<?>> _newArrayList = CollectionLiterals.<NextwebOperation<?>>newArrayList(baseNode, _appendSafe_1, _appendSafe_3);
        opscb.onSuccess(_newArrayList);
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
    this.utils.deleteNodes(metadata, cachedFile, cb);
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String rawFolderName) {
        final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
        final FileOperation _function = new FileOperation() {
          public void apply(final FileOperationContext ctx) {
            final String folderName = FolderToNode.this.futils.toFileSystemSafeName(rawFolderName, false, 100);
            FileItem _folder = ctx.folder();
            _folder.assertFolder(folderName);
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
                  int _hashCode = folderName.hashCode();
                  return Integer.valueOf(_hashCode).toString();
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
    ValueCallback<String> _embed = AsyncCommon.<String>embed(cb, _function);
    this.utils.getFileName(source, _embed);
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ArrayList<FileOperation> _newArrayList = CollectionLiterals.<FileOperation>newArrayList();
    cb.onSuccess(_newArrayList);
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final String folderName = item.name();
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      public void apply(final FileOperationContext ctx) {
        FileItem _folder = ctx.folder();
        _folder.deleteFolder(folderName);
        Metadata _metadata = ctx.metadata();
        _metadata.remove(folderName);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  @Extension
  private ConvertUtils utils = new ConvertUtils();
  
  @Extension
  private FileUtils futils = new FileUtils();
  
  @Extension
  private N n = new N();
}
