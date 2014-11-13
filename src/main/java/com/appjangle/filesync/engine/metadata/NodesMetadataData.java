package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("all")
public class NodesMetadataData implements Serializable, NodesMetadata {
  public List<FileItemMetaData> getChildren() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
