package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import de.mxro.fn.Success;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  private final SyncParams params;
  
  public SyncFolder(final SyncParams params) {
    this.params = params;
  }
  
  private Metadata metadata;
  
  public void doIt(final /* ValueCallback<Success> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nembed cannot be resolved");
  }
  
  public void download(final /* ValueCallback<Success> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nonSuccess cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
