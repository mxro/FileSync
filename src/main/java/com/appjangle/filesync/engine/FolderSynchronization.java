package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.NodeToFolderSynchronizationResult;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.List;

@SuppressWarnings("all")
public class FolderSynchronization {
  public void nodeToFolder(final Node node, final FileItem folder, final ValueCallback<NodeToFolderSynchronizationResult> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: type void is not applicable at this location");
  }
  
  public void determineLocallyChangedFiles(final NodesMetadata metadata, final FileItem folder) {
    final List<FileItemMetaData> cachedChildren = metadata.getChildren();
  }
}
