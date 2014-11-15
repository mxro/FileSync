package com.appjangle.filesync.internal.engine.metadata.v01;

import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@Data
@SuppressWarnings("all")
public class ItemXml {
  private final String name;
  
  private final String uri;
  
  private final String hash;
  
  private final String converter;
  
  public ItemXml(final String name, final String uri, final String hash, final String converter) {
    super();
    this.name = name;
    this.uri = uri;
    this.hash = hash;
    this.converter = converter;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.name== null) ? 0 : this.name.hashCode());
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    result = prime * result + ((this.hash== null) ? 0 : this.hash.hashCode());
    result = prime * result + ((this.converter== null) ? 0 : this.converter.hashCode());
    return result;
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemXml other = (ItemXml) obj;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
      return false;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.hash == null) {
      if (other.hash != null)
        return false;
    } else if (!this.hash.equals(other.hash))
      return false;
    if (this.converter == null) {
      if (other.converter != null)
        return false;
    } else if (!this.converter.equals(other.converter))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("name", this.name);
    b.add("uri", this.uri);
    b.add("hash", this.hash);
    b.add("converter", this.converter);
    return b.toString();
  }
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  @Pure
  public String getUri() {
    return this.uri;
  }
  
  @Pure
  public String getHash() {
    return this.hash;
  }
  
  @Pure
  public String getConverter() {
    return this.converter;
  }
}
