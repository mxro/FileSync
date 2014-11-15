package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl;
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import com.appjangle.filesync.internal.engine.metadata.v01.NodesXml;
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
      final NodesXml nodesXml = ((NodesXml) _fromXML);
      _xblockexpression = MetadataUtilsJre.toMetadata(nodesXml);
    }
    return _xblockexpression;
  }
  
  public static Metadata toMetadata(final NodesXml nodesXml) {
    MetadataImpl _xblockexpression = null;
    {
      final MetadataImpl metadata = new MetadataImpl();
      for (final ItemXml item : nodesXml.items) {
        metadata.add(item);
      }
      _xblockexpression = metadata;
    }
    return _xblockexpression;
  }
}
