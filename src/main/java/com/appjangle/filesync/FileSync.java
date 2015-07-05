package com.appjangle.filesync;

import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.SyncFolder;
import com.appjangle.filesync.internal.engine.convert.ConverterCollection;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.callbacks.ValueCallback;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Session;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileSync {
  public static void syncSingleFolder(final SyncParams params, final /* ValueCallback<Success> */Object cb) {
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
  public static void syncSingleFolder(final File folder, final Node node, final /* ValueCallback<Success> */Object cb) {
    FileItem _wrap = FilesJre.wrap(folder);
    FileSync.syncSingleFolder(_wrap, node, cb);
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final FileItem folder, final Node node, final /* ValueCallback<Success> */Object cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.syncSingleFolder(params, cb);
  }
  
  private static void syncInt(final SyncParams params, final /* ValueCallback<Success> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Success is undefined for the type FileSync"
      + "\nThe method or field Success is undefined for the type FileSync"
      + "\nThe method or field Success is undefined for the type FileSync"
      + "\nThe method or field Success is undefined for the type FileSync"
      + "\nThe method or field Success is undefined for the type FileSync"
      + "\nINSTANCE cannot be resolved"
      + "\nINSTANCE cannot be resolved"
      + "\nINSTANCE cannot be resolved"
      + "\nINSTANCE cannot be resolved"
      + "\nINSTANCE cannot be resolved");
  }
  
  public static void sync(final SyncParams params, final /* ValueCallback<Success> */Object cb) {
    List<Link> _syncRoots = params.getSyncRoots();
    int _size = _syncRoots.size();
    boolean _equals = (_size == 0);
    if (_equals) {
      List<Link> _syncRoots_1 = params.getSyncRoots();
      Node _node = params.getNode();
      Session _session = _node.session();
      Node _node_1 = params.getNode();
      Link _link = _session.link(_node_1);
      _syncRoots_1.add(_link);
    }
    FileSync.syncInt(params, cb);
  }
  
  /**
   * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
   */
  public static void sync(final FileItem folder, final Node node, final /* ValueCallback<Success> */Object cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.sync(params, cb);
  }
  
  public static ConverterCollection createDefaultConverter() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method name is undefined for the type FileSync"
      + "\nThe method visible is undefined for the type FileSync"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nstartsWith cannot be resolved"
      + "\n|| cannot be resolved"
      + "\n! cannot be resolved");
  }
  
  @Extension
  private static FileUtils fileUtils = new FileUtils();
}
