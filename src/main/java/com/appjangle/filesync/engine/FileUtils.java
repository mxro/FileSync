package com.appjangle.filesync.engine;

import com.appjangle.filesync.engine.metadata.Metadata;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import de.mxro.file.FileItem;

@SuppressWarnings("all")
public class FileUtils {
  public Metadata assertMetadata(final FileItem forFolder) {
    Metadata _xblockexpression = null;
    {
      final FileItem metadataFolder = forFolder.assertFolder(".filesync-meta");
      metadataFolder.setVisible(false);
      FileItem _child = metadataFolder.getChild("nodes.xml");
      boolean _exists = _child.exists();
      boolean _not = (!_exists);
      if (_not) {
        metadataFolder.createFile("nodes.xml");
      }
      FileItem _child_1 = metadataFolder.getChild("nodes.xml");
      _xblockexpression = MetadataUtilsJre.readFromFile(_child_1);
    }
    return _xblockexpression;
  }
}
