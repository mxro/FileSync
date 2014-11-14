package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.callbacks.SimpleCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  private final FileItem folder;
  
  private final Node node;
  
  private final Converter converter;
  
  public SyncFolder(final FileItem folder, final Node node, final Converter converter) {
    this.folder = folder;
    this.node = node;
    this.converter = converter;
  }
  
  private Metadata metadata;
  
  public void doIt(final SimpleCallback cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from new ValueCallback(){} to ValueCallback<List<NetworkOperation>>"
      + "\nThe anonymous subclass of ValueCallback does not implement \n- onSuccess(Object)\n- onFailure(Throwable)");
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
}
