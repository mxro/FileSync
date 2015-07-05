package com.appjangle.filesync.internal.engine;

import java.util.List;

import org.eclipse.xtext.xbase.lib.Extension;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.SyncNotifications;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.SynchronizationSettings;

import de.mxro.file.FileItem;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import io.nextweb.Node;

@SuppressWarnings("all")
public class SyncFolder {
  private final SyncParams params;
  
  public SyncFolder(final SyncParams params) {
    this.params = params;
  }
  
  private Metadata metadata;
  
  public void doIt(final /* ValueCallback<Success> */Object cb) {
    SyncNotifications _notifications = this.params.getNotifications();
    FileItem _folder = this.params.getFolder();
    Node _node = this.params.getNode();
    _notifications.onStartSynchronizing(_folder, _node);
    FileItem _folder_1 = this.params.getFolder();
    boolean _hasMetadata = this.fileUtils.hasMetadata(_folder_1);
    boolean _not = (!_hasMetadata);
    if (_not) {
      FileItem _folder_2 = this.params.getFolder();
      Metadata _assertMetadata = this.fileUtils.assertMetadata(_folder_2);
      this.metadata = _assertMetadata;
      this.download(cb);
      return;
    }
    FileItem _folder_3 = this.params.getFolder();
    Metadata _assertMetadata_1 = this.fileUtils.assertMetadata(_folder_3);
    this.metadata = _assertMetadata_1;
    SynchronizationSettings _settings = this.params.getSettings();
    boolean _isUpload = _settings.isUpload();
    boolean _not_1 = (!_isUpload);
    if (_not_1) {
      this.download(cb);
      return;
    }
    SyncValueOperations _syncValueOperations = new SyncValueOperations();
    Node _node_1 = this.params.getNode();
    FileItem _folder_4 = this.params.getFolder();
    final Closure<Success> _function = new Closure<Success>() {
      @Override
      public void apply(final Success it) {
        FileToNetworkOperations _fileToNetworkOperations = new FileToNetworkOperations(SyncFolder.this.params, SyncFolder.this.metadata);
        final Closure<List<NetworkOperation>> _function = new Closure<List<NetworkOperation>>() {
          @Override
          public void apply(final List<NetworkOperation> ops) {
            Node _node = SyncFolder.this.params.getNode();
            final Closure<Success> _function = new Closure<Success>() {
              @Override
              public void apply(final Success it) {
                SyncFolder.this.download(cb);
              }
            };
            ValueCallback<Success> _embed = AsyncCommon.<Success>embed(cb, _function);
            SyncFolder.this.networkUtils.execute(ops, _node, _embed);
          }
        };
        ValueCallback<List<NetworkOperation>> _embed = AsyncCommon.<List<NetworkOperation>>embed(cb, _function);
        _fileToNetworkOperations.determineOps(_embed);
      }
    };
    ValueCallback<Success> _embed = AsyncCommon.<Success>embed(cb, _function);
    _syncValueOperations.uploadValue(_node_1, this.metadata, _folder_4, _embed);
  }
  
  public void download(final /* ValueCallback<Success> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Success is undefined for the type SyncFolder"
      + "\nThe method or field Success is undefined for the type SyncFolder"
      + "\nINSTANCE cannot be resolved"
      + "\nINSTANCE cannot be resolved");
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
