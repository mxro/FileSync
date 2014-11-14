package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.convert.ConvertUtils;
import com.appjangle.filesync.engine.metadata.ItemMetadata;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from Query to Deferred<Object>"
      + "\nType mismatch: cannot convert from Query to Deferred<Object>"
      + "\nType mismatch: cannot convert from Query to Deferred<Object>");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from Query to Deferred<Object>[]");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from NextwebPromise<Success> to Deferred<Object>[]");
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
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ItemMetadata _get = metadata.get(source);
    final String fileName = _get.name();
    final String content = source.<String>value(String.class);
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      public void apply(final FileOperationContext ctx) {
        FileItem _folder = ctx.folder();
        final FileItem file = _folder.getChild(fileName);
        file.setText(content);
        Metadata _metadata = ctx.metadata();
        _metadata.update(new ItemMetadata() {
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
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ItemMetadata _get = metadata.get(source);
    final String fileName = _get.name();
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
}
