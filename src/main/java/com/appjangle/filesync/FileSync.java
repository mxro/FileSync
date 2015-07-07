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
  public static void syncSingleFolder(final File folder, final /* Node */Object node, final ValueCallback<Success> cb) {
    FileItem _wrap = FilesJre.wrap(folder);
    FileSync.syncSingleFolder(_wrap, node, cb);
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final FileItem folder, final /* Node */Object node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.syncSingleFolder(params, cb);
  }
  
  private static void syncInt(final SyncParams params, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nLink cannot be resolved to a type."
      + "\nLink cannot be resolved to a type."
      + "\nThe method exception is undefined for the type FileSync"
      + "\nThe method uri is undefined for the type FileSync"
      + "\nThe method uri is undefined for the type FileSync"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nsecret cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nsecret cannot be resolved"
      + "\nlength cannot be resolved"
      + "\n> cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nsecret cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved"
      + "\nstartsWith cannot be resolved"
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
  public static void sync(final FileItem folder, final /* Node */Object node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.sync(params, cb);
  }
  
  public static ConverterCollection createDefaultConverter() {
    throw new Error("Unresolved compilation problems:"
      + "\nToken cannot be resolved to a type."
      + "\nThe method value is undefined for the type FileSync"
      + "\nThe method uri is undefined for the type FileSync");
  }
  
  @Extension
  private static FileUtils fileUtils = new FileUtils();
}
