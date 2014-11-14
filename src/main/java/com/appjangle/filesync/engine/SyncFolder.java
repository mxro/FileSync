package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.FileToNetworkOperations;
import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.NetworkUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.callbacks.SimpleCallback;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
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
  
  public void doIt(final SimpleCallback cb) {
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
    _fileToNetworkOperations.determineOps(new ValueCallback<List<NetworkOperation>>() {
      public void onSuccess(final List<NetworkOperation> ops) {
        SyncFolder.this.networkUtils.execute(ops);
      }
      
      public void onFailure(final Throwable t) {
        cb.onFailure(t);
      }
    });
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
