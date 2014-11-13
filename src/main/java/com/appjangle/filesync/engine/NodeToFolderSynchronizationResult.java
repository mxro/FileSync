package com.appjangle.filesync.engine;

import de.mxro.file.FileItem;
import java.util.List;

@SuppressWarnings("all")
public interface NodeToFolderSynchronizationResult {
  public abstract boolean isSuccess();
  
  public abstract Throwable exception();
  
  public abstract List<FileItem> childFiles();
}
