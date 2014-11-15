package com.appjangle.filesync.internal.engine.metadata.v01;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

/* @Data
 */@SuppressWarnings("all")
public class ItemXml {
  private final String _name;
  
  public String getName() {
    return this._name;
  }
  
  private final String _uri;
  
  public String getUri() {
    return this._uri;
  }
  
  private final String _hash;
  
  public String getHash() {
    return this._hash;
  }
  
  private final String _converter;
  
  public String getConverter() {
    return this._converter;
  }
  
  public ItemXml(final String name, final String uri, final String hash, final String converter) {
    super();
    this._name = name;
    this._uri = uri;
    this._hash = hash;
    this._converter = converter;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_name== null) ? 0 : _name.hashCode());
    result = prime * result + ((_uri== null) ? 0 : _uri.hashCode());
    result = prime * result + ((_hash== null) ? 0 : _hash.hashCode());
    result = prime * result + ((_converter== null) ? 0 : _converter.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemXml other = (ItemXml) obj;
    if (_name == null) {
      if (other._name != null)
        return false;
    } else if (!_name.equals(other._name))
      return false;
    if (_uri == null) {
      if (other._uri != null)
        return false;
    } else if (!_uri.equals(other._uri))
      return false;
    if (_hash == null) {
      if (other._hash != null)
        return false;
    } else if (!_hash.equals(other._hash))
      return false;
    if (_converter == null) {
      if (other._converter != null)
        return false;
    } else if (!_converter.equals(other._converter))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
}
