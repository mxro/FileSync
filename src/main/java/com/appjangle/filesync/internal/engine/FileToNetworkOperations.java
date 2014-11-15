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
import java.util.List;
import java.util.function.Consumer;

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
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n! cannot be resolved."
      + "\n+ cannot be resolved."
      + "\nThe method filter is undefined for the type FileToNetworkOperations"
      + "\n! cannot be resolved."
      + "\nstartsWith cannot be resolved");
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
        FileItem _child = FileToNetworkOperations.this.folder.getChild(fileName);
        ValueCallback<List<NetworkOperation>> _createCallback = agg.createCallback();
        FileToNetworkOperations.this.converter.update(FileToNetworkOperations.this.metadata, _child, _createCallback);
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method size is undefined for the type FileToNetworkOperations");
  }
  
  public static ArrayList<String> determineLocallyChangedFiles(final Metadata metadata, final FileItem folder) {
    throw new Error("Unresolved compilation problems:"
      + "\n> cannot be resolved.");
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
