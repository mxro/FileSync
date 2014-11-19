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
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
        
        public Date lastModified() {
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
        
        public String uri() {
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
        
        public String hash() {
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
        
        public String converter() {
          throw new UnsupportedOperationException("TODO: auto-generated method stub");
        }
      });
      return;
    }
  }
}
