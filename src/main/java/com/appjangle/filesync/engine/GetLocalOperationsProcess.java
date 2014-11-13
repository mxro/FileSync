package com.appjangle.filesync.engine;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import com.google.common.base.Objects;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class GetLocalOperationsProcess {
  private final Convert convert = null;
  
  private final Node node = null;
  
  private final FileItem folder = null;
  
  public ArrayList<NetworkOperation> getLocalOperations(final ValueCallback<List<NetworkOperation>> cb) {
    try {
      boolean _isDirectory = this.folder.isDirectory();
      boolean _not = (!_isDirectory);
      if (_not) {
        throw new Exception(("File passed and not directory. " + this.folder));
      }
      boolean _exists = this.folder.exists();
      boolean _not_1 = (!_exists);
      if (_not_1) {
        throw new Exception(("File passed does not exist. " + this.folder));
      }
      final FileItem metadata = this.folder.assertFolder(".filesync-meta");
      metadata.setVisible(false);
      FileItem _child = metadata.getChild("nodes.xml");
      final NodesMetadata nodes = MetadataUtilsJre.readFromFile(_child);
      boolean _equals = Objects.equal(nodes, null);
      if (_equals) {
        return new ArrayList<NetworkOperation>(0);
      }
      final ArrayList<String> locallyAddedFiles = GetLocalOperationsProcess.determineLocallyAddedFiles(nodes, this.folder);
      final ArrayList<String> locallyRemovedFiles = GetLocalOperationsProcess.determineLocallyRemovedFiles(nodes, this.folder);
      final ArrayList<String> locallyChangedFiles = GetLocalOperationsProcess.determineLocallyChangedFiles(nodes, this.folder);
      LinkedList<NetworkOperation> _linkedList = new LinkedList<NetworkOperation>();
      this.createOperationsFromChangedFiles(locallyChangedFiles, 0, _linkedList, new ValueCallback<List<NetworkOperation>>() {
        public void onSuccess(final List<NetworkOperation> value) {
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
        
        public void onFailure(final Throwable t) {
          cb.onFailure(t);
        }
      });
      return null;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createOperationsFromChangedFiles(final List<String> fileNames, final int idx, final List<NetworkOperation> res, final ValueCallback<List<NetworkOperation>> cb) {
    int _size = fileNames.size();
    boolean _greaterEqualsThan = (idx >= _size);
    if (_greaterEqualsThan) {
      cb.onSuccess(res);
      return;
    }
    String _get = fileNames.get(idx);
    FileItem _child = this.folder.getChild(_get);
    this.convert.update(_child, this.node, cb);
  }
  
  public static ArrayList<String> determineLocallyChangedFiles(final NodesMetadata metadata, final FileItem folder) {
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
  
  public static ArrayList<String> determineLocallyAddedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetaData> _children = metadata.getChildren();
    final ArrayList<String> previousNames = GetLocalOperationsProcess.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = GetLocalOperationsProcess.getNames(_children_1);
    currentNames.removeAll(previousNames);
    return currentNames;
  }
  
  public static ArrayList<String> determineLocallyRemovedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetaData> _children = metadata.getChildren();
    final ArrayList<String> previousNames = GetLocalOperationsProcess.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = GetLocalOperationsProcess.getNames(_children_1);
    previousNames.removeAll(currentNames);
    return previousNames;
  }
  
  public static ArrayList<String> getNamesFromCache(final List<FileItemMetaData> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final FileItemMetaData fileItemMetaData : cachedChildren) {
      String _name = fileItemMetaData.name();
      res.add(_name);
    }
    return res;
  }
  
  public static ArrayList<String> getNames(final List<FileItem> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final FileItem fileItem : cachedChildren) {
      String _name = fileItem.getName();
      res.add(_name);
    }
    return res;
  }
}
