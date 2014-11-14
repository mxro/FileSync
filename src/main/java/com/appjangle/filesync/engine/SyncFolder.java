package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.engine.FileToNetworkOperations;
import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;
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
  
  public void doIt() {
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
    new FileToNetworkOperations(this.node, this.folder, this.metadata, this.converter);
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
}
