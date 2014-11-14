package com.appjangle.filesync.engine;

import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.engine.metadata.Metadata;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import com.google.common.base.Objects;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SyncFolder {
  public ArrayList<NetworkOperation> doIt(final FileItem folder, final Node node) {
    final FileItem metadataFolder = folder.assertFolder(".filesync-meta");
    metadataFolder.setVisible(false);
    FileItem _child = metadataFolder.getChild("nodes.xml");
    final Metadata metadata = MetadataUtilsJre.readFromFile(_child);
    boolean _equals = Objects.equal(metadata, null);
    if (_equals) {
      return new ArrayList<NetworkOperation>(0);
    }
    return null;
  }
}
