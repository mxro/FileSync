package com.appjangle.filesync;

import com.appjangle.filesync.Metadata;
import de.mxro.file.FileItem;

@SuppressWarnings("all")
public interface FileOperationContext {
  public abstract FileItem folder();
  
  public abstract Metadata metadata();
}
