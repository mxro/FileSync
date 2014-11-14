package com.appjangle.filesync;

import java.util.Date;

@SuppressWarnings("all")
public interface ItemMetadata {
  public abstract String name();
  
  public abstract Date lastModified();
  
  public abstract String uri();
  
  public abstract String hash();
}
