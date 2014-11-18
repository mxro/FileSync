package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
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
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Determines operations performed on local files which need to be uploaded to the cloud.
 */
@SuppressWarnings("all")
public class FileToNetworkOperations {
  private final Node node;
  
  private final FileItem folder;
  
  private final Metadata metadata;
  
  private final Converter converter;
  
  public FileToNetworkOperations(final Node node, final FileItem folder, final Metadata metadata, final Converter converter) {
    this.node = node;
    this.folder = folder;
    this.metadata = metadata;
    this.converter = converter;
  }
  
  public void determineOps(final ValueCallback<List<NetworkOperation>> cb) {
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
      Iterable<String> locallyAddedFiles = FileToNetworkOperations.determineLocallyAddedFiles(this.metadata, this.folder);
      final ArrayList<String> locallyRemovedFiles = FileToNetworkOperations.determineLocallyRemovedFiles(this.metadata, this.folder);
      final ArrayList<String> locallyChangedFiles = FileToNetworkOperations.determineLocallyChangedFiles(this.metadata, this.folder);
      final Function1<String, Boolean> _function = new Function1<String, Boolean>() {
        public Boolean apply(final String fileName) {
          boolean _startsWith = fileName.startsWith(".");
          if (_startsWith) {
            return Boolean.valueOf(false);
          }
          FileItem _get = FileToNetworkOperations.this.folder.get(fileName);
          boolean _visible = _get.getVisible();
          boolean _not = (!_visible);
          if (_not) {
            return Boolean.valueOf(false);
          }
          return Boolean.valueOf(true);
        }
      };
      Iterable<String> _filter = IterableExtensions.<String>filter(locallyAddedFiles, _function);
      locallyAddedFiles = _filter;
      final Closure<List<List<NetworkOperation>>> _function_1 = new Closure<List<List<NetworkOperation>>>() {
        public void apply(final List<List<NetworkOperation>> res) {
          final List<NetworkOperation> ops = CollectionsUtils.<NetworkOperation>flatten(res);
          cb.onSuccess(ops);
        }
      };
      ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function_1);
      final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(3, _embed);
      ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
      this.createOperationsFromRemovedFiles(locallyRemovedFiles, _createCallback);
      ValueCallback<List<NetworkOperation>> _createCallback_1 = agg.createCallback();
      this.createOperationsFromChangedFiles(locallyChangedFiles, _createCallback_1);
      ValueCallback<List<NetworkOperation>> _createCallback_2 = agg.createCallback();
      this.createOperationsFromCreatedFiles(locallyAddedFiles, _createCallback_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createOperationsFromChangedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    int _size = fileNames.size();
    final Closure<List<List<NetworkOperation>>> _function = new Closure<List<List<NetworkOperation>>>() {
      public void apply(final List<List<NetworkOperation>> res) {
        List<NetworkOperation> _flatten = CollectionsUtils.<NetworkOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function);
    final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(_size, _embed);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String fileName) {
        FileItem _get = FileToNetworkOperations.this.folder.get(fileName);
        ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
        FileToNetworkOperations.this.converter.update(FileToNetworkOperations.this.metadata, _get, _createCallback);
      }
    };
    fileNames.forEach(_function_1);
  }
  
  public void createOperationsFromRemovedFiles(final List<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    int _size = fileNames.size();
    final Closure<List<List<NetworkOperation>>> _function = new Closure<List<List<NetworkOperation>>>() {
      public void apply(final List<List<NetworkOperation>> res) {
        List<NetworkOperation> _flatten = CollectionsUtils.<NetworkOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function);
    final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(_size, _embed);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String fileName) {
        ItemMetadata _get = FileToNetworkOperations.this.metadata.get(fileName);
        ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
        FileToNetworkOperations.this.converter.deleteNodes(FileToNetworkOperations.this.metadata, _get, _createCallback);
      }
    };
    fileNames.forEach(_function_1);
  }
  
  public void createOperationsFromCreatedFiles(final Iterable<String> fileNames, final ValueCallback<List<NetworkOperation>> cb) {
    int _size = IterableExtensions.size(fileNames);
    final Closure<List<List<NetworkOperation>>> _function = new Closure<List<List<NetworkOperation>>>() {
      public void apply(final List<List<NetworkOperation>> res) {
        List<NetworkOperation> _flatten = CollectionsUtils.<NetworkOperation>flatten(res);
        cb.onSuccess(_flatten);
      }
    };
    ValueCallback<List<List<NetworkOperation>>> _embed = Async.<List<List<NetworkOperation>>>embed(cb, _function);
    final Aggregator<List<NetworkOperation>> agg = Async.<List<NetworkOperation>>collect(_size, _embed);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String fileName) {
        FileItem _get = FileToNetworkOperations.this.folder.get(fileName);
        ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
        FileToNetworkOperations.this.converter.createNodes(FileToNetworkOperations.this.metadata, _get, _createCallback);
      }
    };
    fileNames.forEach(_function_1);
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
