package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  private final FileItem folder;
  
  private final Node node;
  
  public SyncFolder(final FileItem folder, final Node node) {
    this.folder = folder;
    this.node = node;
  }
  
  public void doIt() {
    boolean _hasMetadata = this.fileUtils.hasMetadata(this.folder);
    boolean _not = (!_hasMetadata);
    if (_not) {
      this.fullDownload();
      return;
    }
    final Metadata metadata = this.fileUtils.assertMetadata(this.folder);
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
}
