package com.appjangle.filesync.internal.engine.metadata.v01;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class ItemXml {
  @Accessors
  private String name;
  
  private String uri;
  
  private String hash;
  
  private String converter;
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
}
