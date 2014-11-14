package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.engine.metadata.Metadata;
import com.thoughtworks.xstream.XStream;
import de.mxro.file.FileItem;

@SuppressWarnings("all")
public class MetadataUtilsJre {
  public static Metadata readFromFile(final FileItem file) {
    Metadata _xblockexpression = null;
    {
      boolean _exists = file.exists();
      boolean _not = (!_exists);
      if (_not) {
        return null;
      }
      final XStream xstream = new XStream();
      String _text = file.getText();
      Object _fromXML = xstream.fromXML(_text);
      final Metadata nodesMetadata = ((Metadata) _fromXML);
      _xblockexpression = nodesMetadata;
    }
    return _xblockexpression;
  }
}
