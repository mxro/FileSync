package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.SyncParams;
import de.mxro.file.FileItem;
import delight.async.callbacks.ValueCallback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Determines operations performed on local files which need to be uploaded to the cloud.
 */
@SuppressWarnings("all")
public class FileToNetworkOperations {
  private final SyncParams params;
  
  private final Metadata metadata;
  
  public FileToNetworkOperations(final SyncParams params, final Metadata metadata) {
    this.params = params;
    this.metadata = metadata;
  }
  
  public void determineOps(final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible"
      + "\nThe field folder is not visible");
  }
  
  public void createOperationsFromChangedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible"
      + "\nThe field folder is not visible");
  }
  
  public void createOperationsFromRemovedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible");
  }
  
  public void createOperationsFromCreatedFiles(final Iterable<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field converter is not visible"
      + "\nThe field folder is not visible");
  }
  
  public static ArrayList<String> determineLocallyChangedFiles(final Metadata metadata, final FileItem folder) {
    ArrayList<String> _xblockexpression = null;
    {
      final ArrayList<String> res = new ArrayList<String>(0);
      List<ItemMetadata> _children = metadata.getChildren();
      for (final ItemMetadata fileMetadata : _children) {
        {
          String _name = fileMetadata.name();
          final FileItem itemNow = folder.get(_name);
          boolean _exists = itemNow.exists();
          if (_exists) {
            Date _lastModified = itemNow.lastModified();
            long _time = _lastModified.getTime();
            Date _lastModified_1 = fileMetadata.lastModified();
            long _time_1 = _lastModified_1.getTime();
            boolean _greaterThan = (_time > _time_1);
            if (_greaterThan) {
              String _name_1 = itemNow.getName();
              res.add(_name_1);
            }
          }
        }
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public static ArrayList<String> determineLocallyAddedFiles(final Metadata metadata, final FileItem folder) {
    List<ItemMetadata> _children = metadata.getChildren();
    final ArrayList<String> previousNames = FileToNetworkOperations.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = FileToNetworkOperations.getNames(_children_1);
    currentNames.removeAll(previousNames);
    return currentNames;
  }
  
  public static ArrayList<String> determineLocallyRemovedFiles(final Metadata metadata, final FileItem folder) {
    List<ItemMetadata> _children = metadata.getChildren();
    final ArrayList<String> previousNames = FileToNetworkOperations.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = FileToNetworkOperations.getNames(_children_1);
    previousNames.removeAll(currentNames);
    return previousNames;
  }
  
  public static ArrayList<String> getNamesFromCache(final List<ItemMetadata> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final ItemMetadata fileItemMetaData : cachedChildren) {
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
