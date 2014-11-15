package com.appjangle.filesync.internal.engine.metadata.v01;

import com.appjangle.filesync.ItemMetadata;
import java.util.Date;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class ItemXml implements ItemMetadata {
  @Accessors
  private String name;
  
  @Accessors
  private Date lastModified;
  
  @Accessors
  private String uri;
  
  @Accessors
  private String hash;
  
  @Accessors
  private String converter;
  
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
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  @Pure
  public Date getLastModified() {
    return this.lastModified;
  }
  
  public void setLastModified(final Date lastModified) {
    this.lastModified = lastModified;
  }
  
  @Pure
  public String getUri() {
    return this.uri;
  }
  
  public void setUri(final String uri) {
    this.uri = uri;
  }
  
  @Pure
  public String getHash() {
    return this.hash;
  }
  
  public void setHash(final String hash) {
    this.hash = hash;
  }
  
  @Pure
  public String getConverter() {
    return this.converter;
  }
  
  public void setConverter(final String converter) {
    this.converter = converter;
  }
}
