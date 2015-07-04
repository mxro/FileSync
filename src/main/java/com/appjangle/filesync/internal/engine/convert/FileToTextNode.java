package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  public boolean worksOn(final FileItem source) {
    String _name = source.getName();
    return this.cutils.isTextValue(_name);
  }
  
  public void worksOn(final Node node, final /* ValueCallback<Boolean> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonFailure cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public void update(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    this.cutils.deleteNodes(metadata, cachedFile, cb);
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved");
  }
  
  @Extension
  private ConvertUtils cutils = new ConvertUtils();
  
  @Extension
  private FileUtils futils = new FileUtils();
}
