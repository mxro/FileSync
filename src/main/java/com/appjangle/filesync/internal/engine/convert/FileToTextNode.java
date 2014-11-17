package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
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
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.utils.data.NextwebDataExtension;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      final String name = source.getName();
      _xblockexpression = name.endsWith(".html");
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    String _HTML_VALUE = N.HTML_VALUE();
    final List<String> textNodeTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_HTML_VALUE));
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
          String _uri = link.uri();
          boolean _contains = textNodeTypes.contains(_uri);
          if (_contains) {
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method parent is undefined for the type FileToTextNode"
      + "\nType mismatch: cannot convert from (Object)=>ArrayList<Object> to NetworkOperation"
      + "\nappendSafe cannot be resolved"
      + "\nappendLabel cannot be resolved"
      + "\nappendTypes cannot be resolved");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method session is undefined for the type FileToTextNode"
      + "\nType mismatch: cannot convert from (Object)=>ArrayList<Object> to NetworkOperation"
      + "\nlink cannot be resolved"
      + "\nsetValueSafe cannot be resolved");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nDuplicate local variable cb"
      + "\nType mismatch: cannot convert from ValueCallback<List<Deferred<?>>> to ValueCallback<List<NextwebPromise<Success>>>");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String ext) {
        final Closure<String> _function = new Closure<String>() {
          public void apply(final String rawFileName) {
            final String fileName = FileToTextNode.this.futils.toFileSystemSafeName(rawFileName, false, 20);
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
        final FileItem file = _folder.getChild(fileName);
        String _text = file.getText();
        boolean _notEquals = (!Objects.equal(_text, content));
        if (_notEquals) {
          file.setText(content);
          Metadata _metadata = ctx.metadata();
          _metadata.update(new ItemMetadata() {
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
  
  @Extension
  private NextwebDataExtension nutils = new NextwebDataExtension();
}
