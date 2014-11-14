package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.ItemMetadata;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Metadata {
  public abstract List<ItemMetadata> getChildren();
  
  public abstract ItemMetadata getChild(final String name);
  
  public abstract ItemMetadata getChild(final Node forNode);
  
  public abstract Metadata add(final ItemMetadata itemMetadata);
  
  public abstract Metadata update(final ItemMetadata itemMetadata);
  
  public abstract Metadata remove(final String name);
}
