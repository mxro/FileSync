package com.appjangle.filesync;

import com.appjangle.api.Node;
import com.appjangle.api.nodes.Token;
import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.SyncFolder;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import com.appjangle.filesync.internal.engine.convert.ConverterCollection;
import com.appjangle.filesync.internal.engine.convert.FileToTextNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNothing;
import com.appjangle.filesync.internal.engine.convert.NodeToNothing;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure2;
import delight.functional.Function;
import delight.functional.Success;
import java.io.File;
import java.util.LinkedList;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileSync {
  public static void syncSingleFolder(final SyncParams params, final ValueCallback<Success> cb) {
    SyncFolder _syncFolder = new SyncFolder(params);
    _syncFolder.doIt(cb);
  }
  
  public static SyncParams defaultSyncParams() {
    final SyncParams params = new SyncParams();
    ConverterCollection _createDefaultConverter = FileSync.createDefaultConverter();
    params.setConverter(_createDefaultConverter);
    SynchronizationSettings _synchronizationSettings = new SynchronizationSettings();
    params.setSettings(_synchronizationSettings);
    params.setState(new SynchronizationState() {
    });
    SyncNotifications _syncNotifications = new SyncNotifications();
    params.setNotifications(_syncNotifications);
    LinkedList<Link> _linkedList = new LinkedList<Link>();
    params.setSyncRoots(_linkedList);
    LinkedList<Link> _linkedList_1 = new LinkedList<Link>();
    params.setDontFollow(_linkedList_1);
    return params;
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final File folder, final Node node, final ValueCallback<Success> cb) {
    FileItem _wrap = FilesJre.wrap(folder);
    FileSync.syncSingleFolder(_wrap, node, cb);
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final FileItem folder, final Node node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.syncSingleFolder(params, cb);
  }
  
  private static void syncInt(final SyncParams params, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nuri cannot be resolved");
  }
  
  public static void sync(final SyncParams params, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved");
  }
  
  /**
   * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
   */
  public static void sync(final FileItem folder, final Node node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.sync(params, cb);
  }
  
  public static ConverterCollection createDefaultConverter() {
    ConverterCollection _xblockexpression = null;
    {
      final ConverterCollection coll = new ConverterCollection();
      FileToTextNode _fileToTextNode = new FileToTextNode();
      coll.addConverter(_fileToTextNode);
      final Closure2<Node, ValueCallback<Boolean>> _function = new Closure2<Node, ValueCallback<Boolean>>() {
        @Override
        public void apply(final Node node, final ValueCallback<Boolean> cb) {
          Object _value = node.value();
          cb.onSuccess(Boolean.valueOf((_value instanceof Token)));
        }
      };
      NodeToNothing _nodeToNothing = new NodeToNothing(_function);
      coll.addConverter(_nodeToNothing);
      final Closure2<Node, ValueCallback<Boolean>> _function_1 = new Closure2<Node, ValueCallback<Boolean>>() {
        @Override
        public void apply(final Node node, final ValueCallback<Boolean> cb) {
          String _uri = node.uri();
          String _nameFromUri = ConvertUtils.getNameFromUri(_uri);
          boolean _startsWith = _nameFromUri.startsWith(".");
          cb.onSuccess(Boolean.valueOf(_startsWith));
        }
      };
      NodeToNothing _nodeToNothing_1 = new NodeToNothing(_function_1);
      coll.addConverter(_nodeToNothing_1);
      final Function<FileItem, Boolean> _function_2 = new Function<FileItem, Boolean>() {
        @Override
        public Boolean apply(final FileItem file) {
          boolean _or = false;
          String _name = file.getName();
          boolean _startsWith = _name.startsWith(".");
          if (_startsWith) {
            _or = true;
          } else {
            boolean _visible = file.getVisible();
            boolean _not = (!_visible);
            _or = _not;
          }
          return Boolean.valueOf(_or);
        }
      };
      FolderToNothing _folderToNothing = new FolderToNothing(_function_2);
      coll.addConverter(_folderToNothing);
      FolderToNode _folderToNode = new FolderToNode();
      coll.addConverter(_folderToNode);
      _xblockexpression = coll;
    }
    return _xblockexpression;
  }
  
  @Extension
  private static FileUtils fileUtils = new FileUtils();
}
