package com.appjangle.filesync.internal.engine.metadata.v01;

import org.eclipse.xtend.lib.Property;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class ItemXml {
  @Property
  private String _name;
  
  private String uri;
  
  private String hash;
  
  private String converter;
  
  @Pure
  public String getName() {
    return this._name;
  }
  
  public void setName(final String name) {
    this._name = name;
  }
}
