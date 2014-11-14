package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.ItemMetadata;
import com.appjangle.filesync.engine.metadata.Metadata;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("all")
public class MetadataData implements Serializable, Metadata {
  public List<ItemMetadata> getChildren() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public ItemMetadata get(final String name) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
