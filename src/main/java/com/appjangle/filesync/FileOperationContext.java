package com.appjangle.filesync;

import de.mxro.file.FileItem;

@SuppressWarnings("all")
public interface FileOperationContext {
  public abstract FileItem folder();
  
  public abstract /* Metadata */Object metadata();
}
