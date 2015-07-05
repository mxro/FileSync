package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.file.FileItem;
import delight.async.callbacks.ValueCallback;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FolderToNode implements Converter {
  @Override
  public boolean worksOn(final FileItem source) {
    return source.isDirectory();
  }
  
  @Override
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    cb.onSuccess(Boolean.valueOf(true));
  }
  
  @Override
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method parent is undefined for the type FolderToNode"
      + "\nThe method parent is undefined for the type null"
      + "\nInvalid number of arguments. The method onSuccess(Closure<Success>) is not applicable for the arguments (Object,ArrayList<Object>)"
      + "\nType mismatch: cannot convert from Object to Closure<Success>"
      + "\nType mismatch: cannot convert from (Object, Object)=>SimpleCallback to NetworkOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nLABEL cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nuri cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
  
  @Override
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    ArrayList<NetworkOperation> _newArrayList = CollectionLiterals.<NetworkOperation>newArrayList();
    cb.onSuccess(_newArrayList);
  }
  
  @Override
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    this.utils.deleteNodes(metadata, cachedFile, cb);
  }
  
  @Override
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method folder is undefined for the type FolderToNode"
      + "\nThe method metadata is undefined for the type FolderToNode"
      + "\nType mismatch: cannot convert from (Object)=>Object to FileOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nassertFolder cannot be resolved"
      + "\nadd cannot be resolved");
  }
  
  @Override
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    ArrayList<FileOperation> _newArrayList = CollectionLiterals.<FileOperation>newArrayList();
    cb.onSuccess(_newArrayList);
  }
  
  @Override
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method folder is undefined for the type FolderToNode"
      + "\nThe method metadata is undefined for the type FolderToNode"
      + "\nType mismatch: cannot convert from (Object)=>Object to FileOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\ndeleteFolder cannot be resolved"
      + "\nremove cannot be resolved");
  }
  
  @Extension
  private ConvertUtils utils = new ConvertUtils();
  
  @Extension
  private FileUtils futils = new FileUtils();
  
  @Extension
  private N n = new N();
}
