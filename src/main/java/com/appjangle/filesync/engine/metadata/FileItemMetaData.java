package com.appjangle.filesync.engine.metadata;

import java.util.Date;

@SuppressWarnings("all")
public interface FileItemMetaData {
  public abstract String name();
  
  public abstract Date lastModified();
}
