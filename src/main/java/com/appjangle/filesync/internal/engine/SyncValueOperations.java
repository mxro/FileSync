package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.Date;

@SuppressWarnings("all")
public class SyncValueOperations {
  public void downloadValue(final Node node, final Metadata metadata, final FileItem folder) {
    FileItem _get = folder.get("value.txt");
    boolean _exists = _get.exists();
    boolean _not = (!_exists);
    if (_not) {
      FileItem _createFile = folder.createFile("value.txt");
      Object _value = node.value();
      String _string = _value.toString();
      _createFile.setText(_string);
      metadata.setValue(new ItemMetadata() {
        public String name() {
          return "value.txt";
        }
        
        public Date lastModified() {
          FileItem _get = folder.get("value.txt");
          return _get.lastModified();
        }
        
        public String uri() {
          return node.uri();
        }
        
        public String hash() {
          FileItem _get = folder.get("value.txt");
          return _get.hash();
        }
        
        public String converter() {
          return "";
        }
      });
      return;
    }
  }
}
