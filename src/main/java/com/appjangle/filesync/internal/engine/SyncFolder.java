package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Success;
import io.nextweb.Node;

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
  
  public void doIt(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method hasMetadata is undefined for the type SyncFolder"
      + "\nThe method assertMetadata is undefined for the type SyncFolder"
      + "\nThe method assertMetadata is undefined for the type SyncFolder"
      + "\nThe method execute is undefined for the type SyncFolder"
      + "\n! cannot be resolved");
  }
  
  public void download(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method execute is undefined for the type SyncFolder");
  }
  
  private FileUtils fileUtils = new FileUtils();
  
  private NetworkUtils networkUtils = new NetworkUtils();
}
