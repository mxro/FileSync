package com.appjangle.filesync;

import java.util.List;

import io.nextweb.Node;

@SuppressWarnings("all")
public interface Metadata {
  public abstract List<ItemMetadata> getChildren();
  
  public abstract ItemMetadata get(final String name);
  
  public abstract ItemMetadata get(final Node forNode);
  
  public abstract Metadata add(final ItemMetadata itemMetadata);
  
  public abstract Metadata update(final ItemMetadata itemMetadata);
  
  public abstract Metadata remove(final String name);
  
  public abstract ItemMetadata value();
  
  public abstract Metadata setValue(final ItemMetadata item);
}
