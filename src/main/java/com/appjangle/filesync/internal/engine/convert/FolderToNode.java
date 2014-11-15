package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class FolderToNode implements Converter {
  public boolean worksOn(final FileItem source) {
    return source.isDirectory();
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    cb.onSuccess(Boolean.valueOf(true));
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\n+ cannot be resolved."
      + "\nThe method newArrayList is undefined for the type FolderToNode");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field newArrayList is undefined for the type FolderToNode");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method newArrayList is undefined for the type FolderToNode");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method getFileName is undefined for the type FolderToNode"
      + "\nThe method toFileSystemSafeName is undefined for the type FolderToNode");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field newArrayList is undefined for the type FolderToNode");
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
  
  private ConvertUtils utils = new ConvertUtils();
  
  private FileUtils futils = new FileUtils();
}
