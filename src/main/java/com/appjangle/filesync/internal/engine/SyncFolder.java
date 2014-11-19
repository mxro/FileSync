package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import com.appjangle.filesync.internal.engine.SyncParams;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Success;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  private final SyncParams params;
  
  public SyncFolder(final SyncParams params) {
    this.params = params;
  }
  
  private Metadata metadata;
  
  public void doIt(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field node is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field converter is undefined for the type SyncFolder"
      + "\nThe method or field node is undefined for the type SyncFolder"
      + "\nhasMetadata cannot be resolved"
      + "\n! cannot be resolved"
      + "\nassertMetadata cannot be resolved"
      + "\nassertMetadata cannot be resolved");
  }
  
  public void download(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field node is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field converter is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nsaveMetadata cannot be resolved");
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
