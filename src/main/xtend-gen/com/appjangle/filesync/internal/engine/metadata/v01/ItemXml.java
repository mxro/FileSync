package com.appjangle.filesync.internal.engine.metadata.v01;

import com.appjangle.filesync.ItemMetadata;
import java.io.Serializable;
import java.util.Date;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.annotations.EqualsHashCode;
import org.eclipse.xtext.xbase.lib.Pure;

@EqualsHashCode
@SuppressWarnings("all")
public class ItemXml implements ItemMetadata, Serializable {
  @Accessors
  public String name;
  
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
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type ItemXml is already defined in ItemXml.java.");
  }
  
  @Override
  @Pure
  public int hashCode() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type ItemXml is already defined in ItemXml.java.");
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
