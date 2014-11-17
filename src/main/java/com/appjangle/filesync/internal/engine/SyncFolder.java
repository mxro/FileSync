package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.Metadata;
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
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved");
  }
  
  public void download(final ValueCallback<Success> cb) {
    NetworkToFileOperations _networkToFileOperations = new NetworkToFileOperations(this.node, this.folder, this.metadata, this.converter);
    final Closure<List<FileOperation>> _function = new Closure<List<FileOperation>>() {
      public void apply(final List<FileOperation> ops) {
        SyncFolder.this.fileUtils.execute(ops, SyncFolder.this.folder, SyncFolder.this.metadata);
        SyncFolder.this.fileUtils.saveMetadata(SyncFolder.this.folder, SyncFolder.this.metadata);
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
