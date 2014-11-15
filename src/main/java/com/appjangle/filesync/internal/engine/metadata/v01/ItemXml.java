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
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Date lastModified() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public String uri() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public String hash() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public String converter() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
