package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class MetadataImpl implements Metadata {
  private final List<ItemMetadata> items;
  
  public MetadataImpl() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field newArrayList is undefined for the type MetadataImpl");
  }
  
  public List<ItemMetadata> getChildren() {
    return this.items;
  }
  
  public ItemMetadata get(final String name) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method findFirst is undefined for the type MetadataImpl"
      + "\nname cannot be resolved"
      + "\n== cannot be resolved");
  }
  
  public ItemMetadata get(final Node forNode) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method findFirst is undefined for the type MetadataImpl"
      + "\nuri cannot be resolved"
      + "\n== cannot be resolved");
  }
  
  public Metadata add(final ItemMetadata itemMetadata) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from ItemXml to ItemMetadata");
  }
  
  public Metadata update(final ItemMetadata itemMetadata) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Metadata remove(final String name) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method re is undefined for the type MetadataImpl");
  }
  
  public ItemXml toXml(final ItemMetadata itemMetadata) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field lastModified is undefined for the type MetadataImpl"
      + "\nThe field name is not visible"
      + "\nThe field uri is not visible"
      + "\nThe field hash is not visible");
  }
}
