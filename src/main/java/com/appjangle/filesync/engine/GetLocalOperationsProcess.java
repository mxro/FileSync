package com.appjangle.filesync.engine;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetaData;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
    int _size = fileNames.size();
    final Closure<List<List<NetworkOperation>>> _function = new Closure<List<List<NetworkOperation>>>() {
      public void apply(final List<List<NetworkOperation>> res) {
      }
    };
    ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function);
    final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(_size, _embed);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String fileName) {
        FileItem _child = GetLocalOperationsProcess.this.folder.getChild(fileName);
        ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
        GetLocalOperationsProcess.this.convert.update(_child, GetLocalOperationsProcess.this.node, _createCallback);
      }
    };
    fileNames.forEach(_function_1);
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
