package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.ItemMetadata;
import java.util.List;

@SuppressWarnings("all")
public interface Metadata {
  public abstract List<ItemMetadata> getChildren();
  
  public abstract ItemMetadata getChild(final String name);
  
  public abstract Metadata add(final ItemMetadata itemMetadata);
}
