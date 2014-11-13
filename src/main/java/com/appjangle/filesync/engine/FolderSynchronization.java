package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.NodeToFolderSynchronizationResult;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class FolderSynchronization {
  public void nodeToFolder(final Node node, final FileItem folder, final ValueCallback<NodeToFolderSynchronizationResult> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: type void is not applicable at this location");
  }
  
  public void determineLocallyChangedFiles(final NodesMetadata metadata, final FileItem folder) {
    throw new Error("Unresolved compilation problems:"
      + "\nmismatched input \'}\' expecting \'(\'");
  }
  
  public ArrayList<String> getNames(final List<FileItemMetaData> cachedChildren) {
    ArrayList<String> _xblockexpression = null;
    {
      int _size = cachedChildren.size();
      final ArrayList<String> res = new ArrayList<String>(_size);
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
}
