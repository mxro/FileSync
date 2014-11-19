package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileToNetworkOperations;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.NetworkUtils;
import com.appjangle.filesync.internal.engine.SyncParams;
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
  private final SyncParams params;
  
  public SyncFolder(final SyncParams params) {
    this.params = params;
  }
  
  private Metadata metadata;
  
  public void doIt(final ValueCallback<Success> cb) {
    FileItem _folder = this.params.folder();
    boolean _hasMetadata = this.fileUtils.hasMetadata(_folder);
    boolean _not = (!_hasMetadata);
    if (_not) {
      FileItem _folder_1 = this.params.folder();
      Metadata _assertMetadata = this.fileUtils.assertMetadata(_folder_1);
      this.metadata = _assertMetadata;
      this.download(cb);
      return;
    }
    FileItem _folder_2 = this.params.folder();
    Metadata _assertMetadata_1 = this.fileUtils.assertMetadata(_folder_2);
    this.metadata = _assertMetadata_1;
    FileToNetworkOperations _fileToNetworkOperations = new FileToNetworkOperations(this.params, this.metadata);
    final Closure<List<NetworkOperation>> _function = new Closure<List<NetworkOperation>>() {
      public void apply(final List<NetworkOperation> ops) {
        Node _node = SyncFolder.this.params.node();
        final Closure<Success> _function = new Closure<Success>() {
          public void apply(final Success it) {
            SyncFolder.this.download(cb);
          }
        };
        ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
        SyncFolder.this.networkUtils.execute(ops, _node, _embed);
      }
    };
    ValueCallback<List<NetworkOperation>> _embed = Async.<List<NetworkOperation>>embed(cb, _function);
    _fileToNetworkOperations.determineOps(_embed);
  }
  
  public void download(final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field node is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field converter is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nThe method or field folder is undefined for the type SyncFolder"
      + "\nsaveMetadata cannot be resolved");
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  @Extension
  private NetworkUtils networkUtils = new NetworkUtils();
}
