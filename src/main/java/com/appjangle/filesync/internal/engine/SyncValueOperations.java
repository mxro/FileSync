package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.Date;

@SuppressWarnings("all")
public class SyncValueOperations {
  public static ItemMetadata createMetadata(final Node node, final FileItem forFile) {
    return new ItemMetadata() {
      public String name() {
        return forFile.getName();
      }
      
      public Date lastModified() {
        return forFile.lastModified();
      }
      
      public String uri() {
        return node.uri();
      }
      
      public String hash() {
        return forFile.hash();
      }
      
      public String converter() {
        return "";
      }
    };
  }
  
  public void downloadValue(final Node node, final Metadata metadata, final FileItem folder) {
    FileItem _get = folder.get("value.txt");
    boolean _exists = _get.exists();
    boolean _not = (!_exists);
    if (_not) {
      FileItem _createFile = folder.createFile("value.txt");
      Object _value = node.value();
      String _string = _value.toString();
      _createFile.setText(_string);
      FileItem _get_1 = folder.get("value.txt");
      ItemMetadata _createMetadata = SyncValueOperations.createMetadata(node, _get_1);
      metadata.setValue(_createMetadata);
      return;
    }
  }
}
