package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.NodeToFolderSynchronizationResult;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.Date;
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
      final ArrayList<String> locallyRemovedFiles = this.determineLocallyRemovedFiles(nodes, folder);
      final ArrayList<String> locallyChangedFiles = this.determineLocallyChangedFiles(nodes, folder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public ArrayList<String> determineLocallyChangedFiles(final NodesMetadata metadata, final FileItem folder) {
    ArrayList<String> _xblockexpression = null;
    {
      final ArrayList<String> res = new ArrayList<String>(0);
      List<FileItemMetaData> _children = metadata.getChildren();
      for (final FileItemMetaData fileMetadata : _children) {
        {
          String _name = fileMetadata.name();
          final FileItem item = folder.getChild(_name);
          boolean _exists = item.exists();
          if (_exists) {
            Date _lastModified = item.lastModified();
            long _time = _lastModified.getTime();
            Date _lastModified_1 = fileMetadata.lastModified();
            long _time_1 = _lastModified_1.getTime();
            boolean _greaterThan = (_time > _time_1);
            if (_greaterThan) {
              String _name_1 = item.getName();
              res.add(_name_1);
            }
          }
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public ArrayList<String> determineLocallyAddedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetaData> _children = metadata.getChildren();
    final ArrayList<String> previousNames = this.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = this.getNames(_children_1);
    currentNames.removeAll(previousNames);
    return currentNames;
  }
  
  public ArrayList<String> determineLocallyRemovedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetaData> _children = metadata.getChildren();
    final ArrayList<String> previousNames = this.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = this.getNames(_children_1);
    previousNames.removeAll(currentNames);
    return previousNames;
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
