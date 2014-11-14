package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  @Extension
  private FileUtils fileUtils = new FileUtils();
  
  public void doIt(final FileItem folder, final Node node) {
    final FileItem metadataFolder = folder.assertFolder(".filesync-meta");
    metadataFolder.setVisible(false);
    FileItem _child = metadataFolder.getChild("nodes.xml");
    final Metadata metadata = MetadataUtilsJre.readFromFile(_child);
  }
}
