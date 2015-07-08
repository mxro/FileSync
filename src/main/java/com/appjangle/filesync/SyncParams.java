package com.appjangle.filesync;

import com.appjangle.api.Link;
import com.appjangle.api.Node;
import com.appjangle.filesync.Converter;
import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import de.mxro.file.FileItem;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;

/* @Accessors */@SuppressWarnings("all")
public class SyncParams {
  private FileItem folder;
  
  private Node node;
  
  private SynchronizationSettings settings;
  
  private SynchronizationState state;
  
  private Converter converter;
  
  private SyncNotifications notifications;
  
  /**
   * <p>The Node defined as the root of the synchronization.
   * <p>All nodes under this root will be resolved.
   */
  private List<Link> syncRoots;
  
  /**
   * <p>If these nodes are found as a child node of another node, their children will not be resolved.
   */
  private List<Link> dontFollow;
  
  public SyncParams() {
  }
  
  public SyncParams(final SyncParams params) {
    this.folder = params.folder;
    this.node = params.node;
    this.settings = params.settings;
    this.state = params.state;
    this.converter = params.converter;
    this.notifications = params.notifications;
    this.syncRoots = params.syncRoots;
    this.dontFollow = params.dontFollow;
  }
}
