package com.appjangle.filesync.internal.engine.metadata.v01;

import com.appjangle.filesync.ItemMetadata;
import java.io.Serializable;
import java.util.Date;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.annotations.EqualsHashCode;

/* @EqualsHashCode */@SuppressWarnings("all")
public class ItemXml implements ItemMetadata, Serializable {
  /* @Accessors
   */public String name;
  
  @Accessors
  public Date lastModified;
  
  @Accessors
  public String uri;
  
  @Accessors
  public String hash;
  
  @Accessors
  public String converter;
  
  @Override
  public String name() {
    return this.name;
  }
  
  @Override
  public Date lastModified() {
    return this.lastModified;
  }
  
  @Override
  public String uri() {
    return this.uri;
  }
  
  @Override
  public String hash() {
    return this.hash;
  }
  
  @Override
  public String converter() {
    return this.converter;
  }
}
