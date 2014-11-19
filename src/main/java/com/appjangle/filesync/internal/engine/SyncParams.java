package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class SyncParams {
  private FileItem folder;
  
  private Node node;
  
  private SynchronizationSettings settings;
  
  private SynchronizationState state;
  
  private Converter converter;
  
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
}
