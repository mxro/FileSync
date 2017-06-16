package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.api.Link;
import com.appjangle.api.LinkList;
import com.appjangle.api.LinkListQuery;
import com.appjangle.api.Node;
import com.appjangle.api.Query;
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
import de.mxro.file.FileItem;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import io.nextweb.promise.DataOperation;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  @Override
  public boolean worksOn(final FileItem source) {
    return this.cutils.isTextValue(source.getName());
  }
  
  @Override
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    final LinkListQuery qry = node.selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      @Override
      public void onFailure(final ExceptionResult er) {
        cb.onFailure(er.exception());
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      @Override
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
  
  @Override
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final String nameWithoutExtension = this.futils.removeExtension(source.getName());
    final String simpleName = this.futils.getSimpleName(nameWithoutExtension);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      @Override
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<DataOperation<?>>> opscb) {
        final Query baseNode = ctx.parent().appendSafe(source.getText(), ("./" + simpleName));
        metadata.add(
          new ItemMetadata() {
            @Override
            public String name() {
              return source.getName();
            }
            
            @Override
            public Date lastModified() {
              return source.lastModified();
            }
            
            @Override
            public String uri() {
              String _uri = ctx.parent().uri();
              String _plus = (_uri + "/");
              return (_plus + simpleName);
            }
            
            @Override
            public String hash() {
              return source.hash();
            }
            
            @Override
            public String converter() {
              return FileToTextNode.this.getClass().toString();
            }
          });
        final ArrayList<DataOperation<?>> res = CollectionLiterals.<DataOperation<?>>newArrayList();
        res.add(baseNode);
        res.add(FileToTextNode.this.cutils.appendLabel(baseNode, nameWithoutExtension));
        res.addAll(FileToTextNode.this.cutils.appendTypesAndIcon(baseNode, source));
        opscb.onSuccess(res);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  @Override
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final String content = source.getText();
    final String address = metadata.get(source.getName()).uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      @Override
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<DataOperation<?>>> opscb) {
        opscb.onSuccess(CollectionLiterals.<DataOperation<?>>newArrayList(ctx.session().link(address).setValueSafe(content)));
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  @Override
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    this.cutils.deleteNodes(metadata, cachedFile, cb);
  }
  
  @Override
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<String> _function = new Closure<String>() {
      @Override
      public void apply(final String ext) {
        final Closure<String> _function = new Closure<String>() {
          @Override
          public void apply(final String rawFileName) {
            final String fileName = FileToTextNode.this.futils.toFileSystemSafeName(rawFileName, false, 100);
            final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
            final FileOperation _function = new FileOperation() {
              @Override
              public void apply(final FileOperationContext ctx) {
                final FileItem file = ctx.folder().createFile(fileName);
                file.setText(source.<String>value(String.class));
                Metadata _metadata = ctx.metadata();
                _metadata.add(
                  new ItemMetadata() {
                    @Override
                    public String name() {
                      return fileName;
                    }
                    
                    @Override
                    public Date lastModified() {
                      return file.lastModified();
                    }
                    
                    @Override
                    public String uri() {
                      return source.uri();
                    }
                    
                    @Override
                    public String hash() {
                      return file.hash();
                    }
                    
                    @Override
                    public String converter() {
                      return FileToTextNode.this.getClass().toString();
                    }
                  });
              }
            };
            ops.add(_function);
            cb.onSuccess(ops);
          }
        };
        FileToTextNode.this.cutils.getFileName(source, folder, ext, 
          AsyncCommon.<String>embed(cb, _function));
      }
    };
    this.cutils.getFileExtension(source, 
      AsyncCommon.<String>embed(cb, _function));
  }
  
  @Override
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final String fileName = metadata.get(source).name();
    final String content = source.<String>value(String.class);
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      @Override
      public void apply(final FileOperationContext ctx) {
        final FileItem file = ctx.folder().get(fileName);
        String _text = file.getText();
        boolean _notEquals = (!Objects.equal(_text, content));
        if (_notEquals) {
          file.setText(content);
          Metadata _metadata = ctx.metadata();
          _metadata.update(
            new ItemMetadata() {
              @Override
              public String name() {
                return fileName;
              }
              
              @Override
              public Date lastModified() {
                return file.lastModified();
              }
              
              @Override
              public String uri() {
                return source.uri();
              }
              
              @Override
              public String hash() {
                return file.hash();
              }
              
              @Override
              public String converter() {
                return FileToTextNode.this.getClass().toString();
              }
            });
        }
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  @Override
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final String fileName = item.name();
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      @Override
      public void apply(final FileOperationContext ctx) {
        ctx.folder().deleteFile(fileName);
        ctx.metadata().remove(fileName);
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
