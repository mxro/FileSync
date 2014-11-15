package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileToNetworkOperations;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkToFileOperations;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Node;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class SyncFolder {
  private final FileItem folder;
  
  private final Node node;
  
  private final Converter converter;
  
  public SyncFolder(final FileItem folder, final Node node, final Converter converter) {
    this.folder = folder;
    this.node = node;
    this.converter = converter;
  }
  
  private Metadata metadata;
  
  public void doIt(final ValueCallback<Success> cb) {
    InputOutput.<String>println("here");
    boolean _hasMetadata = this.fileUtils.hasMetadata(this.folder);
    boolean _not = (!_hasMetadata);
    if (_not) {
      InputOutput.<String>println("and here!");
      Metadata _assertMetadata = this.fileUtils.assertMetadata(this.folder);
      this.metadata = _assertMetadata;
      InputOutput.<String>println("here");
      this.download(cb);
      return;
    }
    Metadata _assertMetadata_1 = this.fileUtils.assertMetadata(this.folder);
    this.metadata = _assertMetadata_1;
    FileToNetworkOperations _fileToNetworkOperations = new FileToNetworkOperations(this.node, this.folder, this.metadata, this.converter);
    final Closure<List<NetworkOperation>> _function = new Closure<List<NetworkOperation>>() {
      public void apply(final List<NetworkOperation> ops) {
        final Closure<Success> _function = new Closure<Success>() {
          public void apply(final Success it) {
            final Closure<Success> _function = new Closure<Success>() {
              public void apply(final Success it) {
                SyncFolder.this.fileUtils.saveForFolder(SyncFolder.this.metadata, SyncFolder.this.folder);
                cb.onSuccess(Success.INSTANCE);
              }
            };
            ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
            SyncFolder.this.download(_embed);
          }
        };
        ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
        SyncFolder.this.networkUtils.execute(ops, SyncFolder.this.node, _embed);
      }
    };
    ValueCallback<List<NetworkOperation>> _embed = Async.<List<NetworkOperation>>embed(cb, _function);
    _fileToNetworkOperations.determineOps(_embed);
  }
  
  public void download(final ValueCallback<Success> cb) {
    NetworkToFileOperations _networkToFileOperations = new NetworkToFileOperations(this.node, this.folder, this.metadata, this.converter);
    final Closure<List<FileOperation>> _function = new Closure<List<FileOperation>>() {
      public void apply(final List<FileOperation> ops) {
        SyncFolder.this.fileUtils.execute(ops, SyncFolder.this.folder, SyncFolder.this.metadata);
        cb.onSuccess(Success.INSTANCE);
      }
    };
    ValueCallback<List<FileOperation>> _embed = Async.<List<FileOperation>>embed(cb, _function);
    _networkToFileOperations.determineOps(_embed);
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
