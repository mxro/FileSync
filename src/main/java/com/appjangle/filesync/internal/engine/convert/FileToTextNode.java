package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import com.google.common.base.Objects;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.LinkList;
import io.nextweb.LinkListQuery;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import mx.gwtutils.MxroGWTUtils;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  public boolean worksOn(final FileItem source) {
    String _name = source.getName();
    return this.cutils.isTextValue(_name);
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    final LinkListQuery qry = node.selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      public void apply(final LinkList links) {
        for (final Link link : links) {
          boolean _isTextType = FileToTextNode.this.cutils.isTextType(link);
          if (_isTextType) {
            cb.onSuccess(Boolean.valueOf(true));
            return;
          }
        }
        cb.onSuccess(Boolean.valueOf(false));
      }
    };
    qry.get(_function_1);
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    String _name = source.getName();
    final String nameWithoutExtension = MxroGWTUtils.removeExtension(_name);
    final String simpleName = MxroGWTUtils.getSimpleName(nameWithoutExtension);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<Deferred<?>>> opscb) {
        Node _parent = ctx.parent();
        String _text = source.getText();
        final Query baseNode = _parent.appendSafe(_text, ("./" + simpleName));
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
              return source.hash();
            }
            
            public String converter() {
              Class<? extends FileToTextNode> _class = FileToTextNode.this.getClass();
              return _class.toString();
            }
          });
        Query _appendLabel = FileToTextNode.this.cutils.appendLabel(baseNode, nameWithoutExtension);
        Query _appendTypesAndIcon = FileToTextNode.this.cutils.appendTypesAndIcon(baseNode, source);
        ArrayList<Deferred<?>> _newArrayList = CollectionLiterals.<Deferred<?>>newArrayList(baseNode, _appendLabel, _appendTypesAndIcon);
        opscb.onSuccess(_newArrayList);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final String content = source.getText();
    String _name = source.getName();
    ItemMetadata _get = metadata.get(_name);
    final String address = _get.uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<Deferred<?>>> opscb) {
        Session _session = ctx.session();
        Link _link = _session.link(address);
        Query _setValueSafe = _link.setValueSafe(content);
        ArrayList<Deferred<?>> _newArrayList = CollectionLiterals.<Deferred<?>>newArrayList(_setValueSafe);
        opscb.onSuccess(_newArrayList);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    this.deleteNodes(metadata, cachedFile, cb);
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String ext) {
        final Closure<String> _function = new Closure<String>() {
          public void apply(final String rawFileName) {
            final String fileName = FileToTextNode.this.futils.toFileSystemSafeName(rawFileName, false, 100);
            final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
            final FileOperation _function = new FileOperation() {
              public void apply(final FileOperationContext ctx) {
                FileItem _folder = ctx.folder();
                final FileItem file = _folder.createFile(fileName);
                String _value = source.<String>value(String.class);
                file.setText(_value);
                Metadata _metadata = ctx.metadata();
                _metadata.add(
                  new ItemMetadata() {
                    public String name() {
                      return fileName;
                    }
                    
                    public Date lastModified() {
                      return file.lastModified();
                    }
                    
                    public String uri() {
                      return source.uri();
                    }
                    
                    public String hash() {
                      return file.hash();
                    }
                    
                    public String converter() {
                      Class<? extends FileToTextNode> _class = FileToTextNode.this.getClass();
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
        FileToTextNode.this.cutils.getFileName(source, folder, ext, _embed);
      }
    };
    ValueCallback<String> _embed = Async.<String>embed(cb, _function);
    this.cutils.getFileExtension(source, _embed);
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ItemMetadata _get = metadata.get(source);
    final String fileName = _get.name();
    final String content = source.<String>value(String.class);
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      public void apply(final FileOperationContext ctx) {
        FileItem _folder = ctx.folder();
        final FileItem file = _folder.get(fileName);
        String _text = file.getText();
        boolean _notEquals = (!Objects.equal(_text, content));
        if (_notEquals) {
          file.setText(content);
          Metadata _metadata = ctx.metadata();
          _metadata.update(
            new ItemMetadata() {
              public String name() {
                return fileName;
              }
              
              public Date lastModified() {
                return file.lastModified();
              }
              
              public String uri() {
                return source.uri();
              }
              
              public String hash() {
                return file.hash();
              }
              
              public String converter() {
                Class<? extends FileToTextNode> _class = FileToTextNode.this.getClass();
                return _class.toString();
              }
            });
        }
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final String fileName = item.name();
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      public void apply(final FileOperationContext ctx) {
        FileItem _folder = ctx.folder();
        _folder.deleteFile(fileName);
        Metadata _metadata = ctx.metadata();
        _metadata.remove(fileName);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  @Extension
  private ConvertUtils cutils = new ConvertUtils();
  
  @Extension
  private FileUtils futils = new FileUtils();
}
