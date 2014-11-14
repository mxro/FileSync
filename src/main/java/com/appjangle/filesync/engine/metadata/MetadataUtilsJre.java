package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.Metadata;
import com.thoughtworks.xstream.XStream;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.JreFiles;
import java.io.InputStream;

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
      InputStream _inputStream = JreFiles.getInputStream(file);
      Object _fromXML = xstream.fromXML(_inputStream);
      final Metadata nodesMetadata = ((Metadata) _fromXML);
      _xblockexpression = nodesMetadata;
    }
    return _xblockexpression;
  }
}
