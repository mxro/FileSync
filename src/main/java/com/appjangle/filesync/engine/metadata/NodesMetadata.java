package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import java.util.List;

@SuppressWarnings("all")
public interface NodesMetadata {
  public abstract List<FileItemMetaData> getChildren();
  
  public abstract FileItemMetaData getChild(final String name);
}
