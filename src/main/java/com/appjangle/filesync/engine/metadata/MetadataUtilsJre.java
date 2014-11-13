package com.appjangle.filesync.engine.metadata;

import com.appjangle.filesync.engine.metadata.NodesMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadataData;
import com.thoughtworks.xstream.XStream;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.JreFiles;
import java.io.InputStream;

@SuppressWarnings("all")
public class MetadataUtilsJre {
  public static NodesMetadata readFromFile(final FileItem file) {
    NodesMetadata _xblockexpression = null;
    {
      boolean _exists = file.exists();
      boolean _not = (!_exists);
      if (_not) {
        return new NodesMetadataData();
      }
      final XStream xstream = new XStream();
      InputStream _inputStream = JreFiles.getInputStream(file);
      Object _fromXML = xstream.fromXML(_inputStream);
      final NodesMetadata nodesMetadata = ((NodesMetadata) _fromXML);
      _xblockexpression = null;
    }
    return _xblockexpression;
  }
}
