package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.engine.metadata.ItemMetadata;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Metadata {
  public abstract List<ItemMetadata> getChildren();
  
  public abstract ItemMetadata get(final String name);
  
  public abstract ItemMetadata get(final Node forNode);
  
  public abstract com.appjangle.filesync.engine.metadata.Metadata add(final ItemMetadata itemMetadata);
  
  public abstract com.appjangle.filesync.engine.metadata.Metadata update(final ItemMetadata itemMetadata);
  
  public abstract com.appjangle.filesync.engine.metadata.Metadata remove(final String name);
}
