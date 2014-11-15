package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl;
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import com.appjangle.filesync.internal.engine.metadata.v01.NodesXml;
import com.thoughtworks.xstream.XStream;
import de.mxro.file.FileItem;
import java.util.Date;
import java.util.List;
import org.eclipse.xtext.xbase.lib.InputOutput;

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
      String _text_1 = file.getText();
      String _plus = ("LOAD! " + _text_1);
      InputOutput.<String>println(_plus);
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
    final NodesXml nodesXml = MetadataUtilsJre.toNodesXml(metadata);
    String _xML = xstream.toXML(nodesXml);
    file.setText(_xML);
    String _text = file.getText();
    String _plus = ("Save !\n" + _text);
    InputOutput.<String>println(_plus);
  }
  
  public static NodesXml toNodesXml(final Metadata metadata) {
    NodesXml _xblockexpression = null;
    {
      NodesXml nodesXml = new NodesXml();
      List<ItemMetadata> _children = metadata.getChildren();
      for (final ItemMetadata item : _children) {
        ItemXml _itemXml = MetadataUtilsJre.toItemXml(item);
        nodesXml.items.add(_itemXml);
      }
      _xblockexpression = nodesXml;
    }
    return _xblockexpression;
  }
  
  public static ItemXml toItemXml(final ItemMetadata item) {
    ItemXml _xblockexpression = null;
    {
      final ItemXml itemXml = new ItemXml();
      String _converter = item.converter();
      itemXml.converter = _converter;
      Date _lastModified = item.lastModified();
      itemXml.lastModified = _lastModified;
      String _hash = item.hash();
      itemXml.hash = _hash;
      String _name = item.name();
      itemXml.name = _name;
      String _uri = item.uri();
      itemXml.uri = _uri;
      _xblockexpression = itemXml;
    }
    return _xblockexpression;
  }
}
