package com.appjangle.filesync.engine;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.engine.metadata.Metadata;
import com.appjangle.filesync.engine.metadata.MetadataUtilsJre;
import de.mxro.file.FileItem;
import java.util.List;

@SuppressWarnings("all")
public class FileUtils {
  public boolean hasMetadata(final FileItem forFolder) {
    FileItem _assertFolder = forFolder.assertFolder(".filesync-meta");
    FileItem _child = _assertFolder.getChild("nodes.xml");
    return _child.exists();
  }
  
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
  
  public void execute(final List<FileOperation> operations) {
    for (final FileOperation op : operations) {
    }
  }
}
