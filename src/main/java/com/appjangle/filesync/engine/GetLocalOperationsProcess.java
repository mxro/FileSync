package com.appjangle.filesync.engine;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public class GetLocalOperationsProcess {
  private final Convert convert = null;
  
  private final Node node = null;
  
  private final FileItem folder = null;
  
  public ArrayList<NetworkOperation> getLocalOperations(final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method createOperationsFromChangedFiles(List<String>, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (ArrayList<String>,int,LinkedList<NetworkOperation>,new ValueCallback<List<NetworkOperation>>(){})"
      + "\nType mismatch: cannot convert from int to ValueCallback<List<NetworkOperation>>");
  }
  
  public void createOperationsFromChangedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nno viable alternative at input \']\'"
      + "\nThe method or field CollectionUtils is undefined for the type GetLocalOperationsProcess");
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
