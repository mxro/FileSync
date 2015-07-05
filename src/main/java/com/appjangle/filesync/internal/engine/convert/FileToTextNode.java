package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.file.FileItem;
import delight.async.callbacks.ValueCallback;
import io.nextweb.Node;
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
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method get() is not applicable for the arguments ((Iterable<? extends Link>)=>void)"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  @Override
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method parent is undefined for the type FileToTextNode"
      + "\nThe method parent is undefined for the type null"
      + "\nInvalid number of arguments. The method onSuccess(Closure<Success>) is not applicable for the arguments (Object,ArrayList<Object>)"
      + "\nType mismatch: cannot convert from Object to Closure<Success>"
      + "\nType mismatch: cannot convert from (Object, Object)=>SimpleCallback to NetworkOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
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
      + "\nThe method session is undefined for the type FileToTextNode"
      + "\nInvalid number of arguments. The method onSuccess(Closure<Success>) is not applicable for the arguments (Object,ArrayList<Object>)"
      + "\nType mismatch: cannot convert from Object to Closure<Success>"
      + "\nType mismatch: cannot convert from (Object, Object)=>SimpleCallback to NetworkOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nlink cannot be resolved"
      + "\nsetValueSafe cannot be resolved");
  }
  
  @Override
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    this.cutils.deleteNodes(metadata, cachedFile, cb);
  }
  
  @Override
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method folder is undefined for the type FileToTextNode"
      + "\nThe method metadata is undefined for the type FileToTextNode"
      + "\nType mismatch: cannot convert from (Object)=>Object to FileOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\ncreateFile cannot be resolved"
      + "\ntext cannot be resolved"
      + "\nadd cannot be resolved"
      + "\nlastModified cannot be resolved"
      + "\nhash cannot be resolved");
  }
  
  @Override
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method folder is undefined for the type FileToTextNode"
      + "\nThe method metadata is undefined for the type FileToTextNode"
      + "\nType mismatch: cannot convert from (Object)=>Object to FileOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nget cannot be resolved"
      + "\ntext cannot be resolved"
      + "\n!= cannot be resolved"
      + "\ntext cannot be resolved"
      + "\nupdate cannot be resolved"
      + "\nlastModified cannot be resolved"
      + "\nhash cannot be resolved");
  }
  
  @Override
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method folder is undefined for the type FileToTextNode"
      + "\nThe method metadata is undefined for the type FileToTextNode"
      + "\nType mismatch: cannot convert from (Object)=>Object to FileOperation"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\ndeleteFile cannot be resolved"
      + "\nremove cannot be resolved");
  }
  
  @Extension
  private ConvertUtils cutils = new ConvertUtils();
  
  @Extension
  private FileUtils futils = new FileUtils();
}
