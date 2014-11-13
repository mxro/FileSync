package com.appjangle.filesync.engine;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import com.google.common.base.Objects;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.collections.CollectionsUtils;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class GetLocalOperationsProcess {
  private final Convert convert = null;
  
  private final Node node = null;
  
  private final FileItem folder = null;
  
  private NodesMetadata nodes = null;
  
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
      NodesMetadata _readFromFile = MetadataUtilsJre.readFromFile(_child);
      this.nodes = _readFromFile;
      boolean _equals = Objects.equal(this.nodes, null);
      if (_equals) {
        return new ArrayList<NetworkOperation>(0);
      }
      final ArrayList<String> locallyAddedFiles = GetLocalOperationsProcess.determineLocallyAddedFiles(this.nodes, this.folder);
      final ArrayList<String> locallyRemovedFiles = GetLocalOperationsProcess.determineLocallyRemovedFiles(this.nodes, this.folder);
      final ArrayList<String> locallyChangedFiles = GetLocalOperationsProcess.determineLocallyChangedFiles(this.nodes, this.folder);
      final Closure<List<List<NetworkOperation>>> _function = new Closure<List<List<NetworkOperation>>>() {
        public void apply(final List<List<NetworkOperation>> res) {
          final List<NetworkOperation> ops = CollectionsUtils.<NetworkOperation>flatten(res);
          cb.onSuccess(ops);
        }
      };
      ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function);
      final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(3, _embed);
      ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
      this.createOperationsFromRemovedFiles(locallyRemovedFiles, _createCallback);
      ValueCallback<List<NetworkOperation>> _createCallback_1 = agg.createCallback();
      this.createOperationsFromChangedFiles(locallyChangedFiles, _createCallback_1);
      ValueCallback<List<NetworkOperation>> _createCallback_2 = agg.createCallback();
      this.createOperationsFromCreatedFiles(locallyAddedFiles, _createCallback_2);
      return null;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createOperationsFromChangedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method update(NodesMetadata, FileItem, Node, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (FileItem,Node,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Node to FileItem"
      + "\nType mismatch: cannot convert from FileItem to NodesMetadata"
      + "\nType mismatch: cannot convert from ValueCallback<List<NetworkOperation>> to Node");
  }
  
  public void createOperationsFromRemovedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method deleteNodes(NodesMetadata, FileItemMetadata, Node, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (FileItemMetadata,Node,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Node to FileItemMetadata"
      + "\nType mismatch: cannot convert from ValueCallback<List<NetworkOperation>> to Node"
      + "\nType mismatch: cannot convert from FileItemMetadata to NodesMetadata");
  }
  
  public void createOperationsFromCreatedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method createNodes(NodesMetadata, FileItem, Node, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (FileItem,Node,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Node to FileItem"
      + "\nType mismatch: cannot convert from FileItem to NodesMetadata"
      + "\nType mismatch: cannot convert from ValueCallback<List<NetworkOperation>> to Node");
  }
  
  public static ArrayList<String> determineLocallyChangedFiles(final NodesMetadata metadata, final FileItem folder) {
    ArrayList<String> _xblockexpression = null;
    {
      final ArrayList<String> res = new ArrayList<String>(0);
      List<FileItemMetadata> _children = metadata.getChildren();
      for (final FileItemMetadata fileMetadata : _children) {
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
    List<FileItemMetadata> _children = metadata.getChildren();
    final ArrayList<String> previousNames = GetLocalOperationsProcess.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = GetLocalOperationsProcess.getNames(_children_1);
    currentNames.removeAll(previousNames);
    return currentNames;
  }
  
  public static ArrayList<String> determineLocallyRemovedFiles(final NodesMetadata metadata, final FileItem folder) {
    List<FileItemMetadata> _children = metadata.getChildren();
    final ArrayList<String> previousNames = GetLocalOperationsProcess.getNamesFromCache(_children);
    List<FileItem> _children_1 = folder.getChildren();
    final ArrayList<String> currentNames = GetLocalOperationsProcess.getNames(_children_1);
    previousNames.removeAll(currentNames);
    return previousNames;
  }
  
  public static ArrayList<String> getNamesFromCache(final List<FileItemMetadata> cachedChildren) {
    int _size = cachedChildren.size();
    final ArrayList<String> res = new ArrayList<String>(_size);
    for (final FileItemMetadata fileItemMetaData : cachedChildren) {
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
