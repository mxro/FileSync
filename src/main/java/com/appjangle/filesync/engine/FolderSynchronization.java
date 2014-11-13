package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.NodeToFolderSynchronizationResult;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class FolderSynchronization {
  public void nodeToFolder(final Node node, final FileItem folder, final ValueCallback<NodeToFolderSynchronizationResult> cb) {
    try {
      boolean _isDirectory = folder.isDirectory();
      boolean _not = (!_isDirectory);
      if (_not) {
        throw new Exception(("File passed and not directory. " + folder));
      }
      boolean _exists = folder.exists();
      boolean _not_1 = (!_exists);
      if (_not_1) {
        throw new Exception(("File passed does not exist. " + folder));
      }
      final FileItem metadata = folder.assertFolder(".filesync-meta");
      metadata.setVisible(false);
      FileItem _child = metadata.getChild("nodes.xml");
      final NodesMetadata nodes = MetadataUtilsJre.readFromFile(_child);
      final ArrayList<String> locallyAddedFiles = this.determineLocallyAddedFiles(nodes, folder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public ArrayList<String> determineLocallyAddedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetaData> _children = metadata.getChildren();
    final ArrayList<String> previousNames = this.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = this.getNames(_children_1);
    currentNames.removeAll(previousNames);
    return currentNames;
  }
  
  public ArrayList<String> getNamesFromCache(final List<FileItemMetaData> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final FileItemMetaData fileItemMetaData : cachedChildren) {
      String _name = fileItemMetaData.name();
      res.add(_name);
    }
    return res;
  }
  
  public ArrayList<String> getNames(final List<FileItem> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final FileItem fileItem : cachedChildren) {
      String _name = fileItem.getName();
      res.add(_name);
    }
    return res;
  }
}
