package com.appjangle.filesync;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import de.mxro.file.FileItem;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class SyncParams {
  private FileItem folder;
  
  private /* Node */Object node;
  
  private SynchronizationSettings settings;
  
  private SynchronizationState state;
  
  private Converter converter;
  
  private SyncNotifications notifications;
  
  /**
   * <p>The Node defined as the root of the synchronization.
   * <p>All nodes under this root will be resolved.
   */
  private /* List<Link> */Object syncRoots;
  
  /**
   * <p>If these nodes are found as a child node of another node, their children will not be resolved.
   */
  private /* List<Link> */Object dontFollow;
  
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
  
  @Pure
  public FileItem getFolder() {
    return this.folder;
  }
  
  public void setFolder(final FileItem folder) {
    this.folder = folder;
  }
  
  @Pure
  public Node getNode() {
    return this.node;
  }
  
  public void setNode(final Node node) {
    this.node = node;
  }
  
  @Pure
  public SynchronizationSettings getSettings() {
    return this.settings;
  }
  
  public void setSettings(final SynchronizationSettings settings) {
    this.settings = settings;
  }
  
  @Pure
  public SynchronizationState getState() {
    return this.state;
  }
  
  public void setState(final SynchronizationState state) {
    this.state = state;
  }
  
  @Pure
  public Converter getConverter() {
    return this.converter;
  }
  
  public void setConverter(final Converter converter) {
    this.converter = converter;
  }
  
  @Pure
  public SyncNotifications getNotifications() {
    return this.notifications;
  }
  
  public void setNotifications(final SyncNotifications notifications) {
    this.notifications = notifications;
  }
  
  @Pure
  public /* List<Link> */Object getSyncRoots() {
    return this.syncRoots;
  }
  
  public void setSyncRoots(final /* List<Link> */Object syncRoots) {
    this.syncRoots = syncRoots;
  }
  
  @Pure
  public /* List<Link> */Object getDontFollow() {
    return this.dontFollow;
  }
  
  public void setDontFollow(final /* List<Link> */Object dontFollow) {
    this.dontFollow = dontFollow;
  }
}
