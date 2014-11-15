package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl;
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import com.appjangle.filesync.internal.engine.metadata.v01.NodesXml;
import com.thoughtworks.xstream.XStream;
import de.mxro.file.FileItem;
import java.util.List;

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
  
  public static void saveToFile(final Metadata metadata, final FileItem file) {
    boolean _exists = file.exists();
    boolean _not = (!_exists);
    if (_not) {
      throw new RuntimeException("File doesn\'t exist.");
    }
    final XStream xstream = new XStream();
  }
  
  public static NodesXml toNodesXml(final Metadata metadata) {
    NodesXml _xblockexpression = null;
    {
      NodesXml nodesXml = new NodesXml();
      List<ItemMetadata> _children = metadata.getChildren();
      for (final ItemMetadata item : _children) {
      }
      _xblockexpression = nodesXml;
    }
    return _xblockexpression;
  }
}
