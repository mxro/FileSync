package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.google.common.base.Objects;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Query;
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
    FileItem _get_2 = folder.get("value.txt");
    final String oldText = _get_2.getText();
    Object _value_1 = node.value();
    String _string_1 = _value_1.toString();
    boolean _notEquals = (!Objects.equal(oldText, _string_1));
    if (_notEquals) {
      FileItem _get_3 = folder.get("value.txt");
      Object _value_2 = node.value();
      String _string_2 = _value_2.toString();
      _get_3.setText(_string_2);
      FileItem _get_4 = folder.get("value.txt");
      ItemMetadata _createMetadata_1 = SyncValueOperations.createMetadata(node, _get_4);
      metadata.setValue(_createMetadata_1);
    }
  }
  
  public Query uploadValue(final Node node, final Metadata metadata, final FileItem folder, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nmissing \')\' at \'}\'");
  }
}
