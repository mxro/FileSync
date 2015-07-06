package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.file.FileItem;
import delight.async.callbacks.ValueCallback;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  @Override
  public boolean worksOn(final FileItem source) {
    String _name = source.getName();
    return this.cutils.isTextValue(_name);
  }
  
  @Override
  public void worksOn(final /* Node */Object node, final ValueCallback<Boolean> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method exception is undefined for the type FileToTextNode"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nselectAllLinks cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  @Override
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from ArrayList<Object> to List<NextwebOperation<?>>"
      + "\nappendSafe cannot be resolved"
      + "\nappendLabel cannot be resolved"
      + "\nappendTypesAndIcon cannot be resolved"
      + "\nuri cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
  
  @Override
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nlink cannot be resolved"
      + "\nsetValueSafe cannot be resolved");
  }
  
  @Override
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    this.cutils.deleteNodes(metadata, cachedFile, cb);
  }
  
  @Override
  public void createFiles(final FileItem folder, final Metadata metadata, final /* Node */Object source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\ngetFileExtension cannot be resolved"
      + "\ngetFileName cannot be resolved"
      + "\nvalue cannot be resolved"
      + "\nuri cannot be resolved");
  }
  
  @Override
  public void updateFiles(final FileItem folder, final Metadata metadata, final /* Node */Object source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nvalue cannot be resolved"
      + "\nuri cannot be resolved");
  }
  
  @Override
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final String fileName = item.name();
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      @Override
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
