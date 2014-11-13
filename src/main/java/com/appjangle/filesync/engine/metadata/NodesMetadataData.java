package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("all")
public class NodesMetadataData implements Serializable, NodesMetadata {
  public List<FileItemMetadata> getChildren() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
