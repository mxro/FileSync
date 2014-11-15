package com.appjangle.filesync.internal.engine.metadata.v01;

import com.appjangle.filesync.ItemMetadata;
import java.util.Date;

@SuppressWarnings("all")
public class ItemXml implements ItemMetadata {
  public String name;
  
  public Date lastModified;
  
  public String uri;
  
  public String hash;
  
  public String converter;
  
  public String name() {
    return this.name;
  }
  
  public Date lastModified() {
    return this.lastModified;
  }
  
  public String uri() {
    return this.uri;
  }
  
  public String hash() {
    return this.hash;
  }
  
  public String converter() {
    return this.converter;
  }
}
