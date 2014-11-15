package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class MetadataImpl implements Metadata {
  private List<ItemMetadata> items;
  
  public List<ItemMetadata> getChildren() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public ItemMetadata get(final String name) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public ItemMetadata get(final Node forNode) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Metadata add(final ItemMetadata itemMetadata) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Metadata update(final ItemMetadata itemMetadata) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Metadata remove(final String name) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
