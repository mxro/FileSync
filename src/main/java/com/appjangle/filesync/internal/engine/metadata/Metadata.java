package com.appjangle.filesync.internal.engine.metadata;

import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public interface Metadata {
  public abstract /* List<com.appjangle.filesync.engine.metadata.ItemMetadata> */Object getChildren();
  
  public abstract /* com.appjangle.filesync.engine.metadata.ItemMetadata */Object get(final String name);
  
  public abstract /* com.appjangle.filesync.engine.metadata.ItemMetadata */Object get(final Node forNode);
  
  public abstract /* com.appjangle.filesync.engine.metadata.Metadata */Object add(final /* com.appjangle.filesync.engine.metadata.ItemMetadata */Object itemMetadata);
  
  public abstract /* com.appjangle.filesync.engine.metadata.Metadata */Object update(final /* com.appjangle.filesync.engine.metadata.ItemMetadata */Object itemMetadata);
  
  public abstract /* com.appjangle.filesync.engine.metadata.Metadata */Object remove(final String name);
}
