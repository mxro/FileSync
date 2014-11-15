package com.appjangle.filesync.internal.engine.metadata.v01;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

@Data
@SuppressWarnings("all")
public class ItemXml {
  private final String _name;
  
  private final String _uri;
  
  private final String _hash;
  
  private final String _converter;
  
  public ItemXml(final String name, final String uri, final String hash, final String converter) {
    super();
    this._name = name;
    this._uri = uri;
    this._hash = hash;
    this._converter = converter;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this._name== null) ? 0 : this._name.hashCode());
    result = prime * result + ((this._uri== null) ? 0 : this._uri.hashCode());
    result = prime * result + ((this._hash== null) ? 0 : this._hash.hashCode());
    result = prime * result + ((this._converter== null) ? 0 : this._converter.hashCode());
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
    if (this._name == null) {
      if (other._name != null)
        return false;
    } else if (!this._name.equals(other._name))
      return false;
    if (this._uri == null) {
      if (other._uri != null)
        return false;
    } else if (!this._uri.equals(other._uri))
      return false;
    if (this._hash == null) {
      if (other._hash != null)
        return false;
    } else if (!this._hash.equals(other._hash))
      return false;
    if (this._converter == null) {
      if (other._converter != null)
        return false;
    } else if (!this._converter.equals(other._converter))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
  
  @Pure
  public String getName() {
    return this._name;
  }
  
  @Pure
  public String getUri() {
    return this._uri;
  }
  
  @Pure
  public String getHash() {
    return this._hash;
  }
  
  @Pure
  public String getConverter() {
    return this._converter;
  }
}
