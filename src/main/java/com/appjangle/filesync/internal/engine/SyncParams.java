package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import de.mxro.file.FileItem;
import io.nextweb.Node;

@SuppressWarnings("all")
public interface SyncParams {
  public abstract FileItem folder();
  
  public abstract Node node();
  
  public abstract SynchronizationSettings settings();
  
  public abstract SynchronizationState state();
  
  public abstract Converter converter();
}
