package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import java.util.List;

@SuppressWarnings("all")
public interface NodesMetadata {
  public abstract List<FileItemMetadata> getChildren();
  
  public abstract FileItemMetadata getChild(final String name);
}
