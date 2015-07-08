package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import delight.async.callbacks.ValueCallback;
import delight.functional.Success;
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
      + "\nThe field notifications is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field node is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field settings is not visible"
      + "\nThe field upload is not visible"
      + "\nThe field node is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field node is not visible");
  }
  
  public void download(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved."
      + "\nThe field settings is not visible"
      + "\nThe field notifications is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field node is not visible"
      + "\nThe field node is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field notifications is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field node is not visible"
      + "\nType mismatch: cannot convert from SynchronizationSettings to ValueCallback<Success>");
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
