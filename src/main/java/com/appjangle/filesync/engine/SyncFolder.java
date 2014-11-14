package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.FileToNetworkOperations;
import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.NetworkUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Node;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;

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
    boolean _hasMetadata = this.fileUtils.hasMetadata(this.folder);
    boolean _not = (!_hasMetadata);
    if (_not) {
      Metadata _assertMetadata = this.fileUtils.assertMetadata(this.folder);
      this.metadata = _assertMetadata;
      this.fullDownload();
      return;
    }
    Metadata _assertMetadata_1 = this.fileUtils.assertMetadata(this.folder);
    this.metadata = _assertMetadata_1;
    FileToNetworkOperations _fileToNetworkOperations = new FileToNetworkOperations(this.node, this.folder, this.metadata, this.converter);
    final Closure<List<NetworkOperation>> _function = new Closure<List<NetworkOperation>>() {
      public void apply(final List<NetworkOperation> ops) {
        final Closure<Success> _function = new Closure<Success>() {
          public void apply(final Success it) {
          }
        };
        ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
        SyncFolder.this.networkUtils.execute(ops, _embed);
      }
    };
    ValueCallback<List<NetworkOperation>> _embed = Async.<List<NetworkOperation>>embed(cb, _function);
    _fileToNetworkOperations.determineOps(_embed);
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
