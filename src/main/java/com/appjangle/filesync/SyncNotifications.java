package com.appjangle.filesync;

import de.mxro.file.FileItem;
import io.nextweb.Link;
import io.nextweb.Node;

@SuppressWarnings("all")
public class SyncNotifications {
  public void onInsufficientAuthorization(final FileItem inFolder, final Link forNode) {
  }
  
  public void onNodeNotDefined(final Node parent, final Link node) {
  }
  
  public void onStartSynchronizing(final FileItem folder, final Node node) {
  }
  
  public void onFinishedSynchronizing(final FileItem folder, final Node node) {
  }
}
