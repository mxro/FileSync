package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl;
import com.appjangle.filesync.internal.engine.metadata.MetadataUtilsJre;
import de.mxro.file.FileItem;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

@SuppressWarnings("all")
public class FileUtils {
  public void saveMetadata(final FileItem forFolder, final Metadata metadata) {
    FileItem _get = forFolder.get(".filesync-meta");
    FileItem _get_1 = _get.get("nodes.xml");
    MetadataUtilsJre.saveToFile(metadata, _get_1);
  }
  
  public boolean hasMetadata(final FileItem forFolder) {
    FileItem _assertFolder = forFolder.assertFolder(".filesync-meta");
    FileItem _get = _assertFolder.get("nodes.xml");
    return _get.exists();
  }
  
  public Metadata assertMetadata(final FileItem forFolder) {
    Metadata _xblockexpression = null;
    {
      final FileItem metadataFolder = forFolder.assertFolder(".filesync-meta");
      metadataFolder.setVisible(false);
      FileItem _get = metadataFolder.get("nodes.xml");
      boolean _exists = _get.exists();
      boolean _not = (!_exists);
      if (_not) {
        final FileItem metadataFile = metadataFolder.createFile("nodes.xml");
        final MetadataImpl metadata = new MetadataImpl();
        MetadataUtilsJre.saveToFile(metadata, metadataFile);
        return metadata;
      }
      _xblockexpression = this.loadMetadata(forFolder);
    }
    return _xblockexpression;
  }
  
  public Metadata loadMetadata(final FileItem forFolder) {
    FileItem _get = forFolder.get(".filesync-meta");
    FileItem _get_1 = _get.get("nodes.xml");
    return MetadataUtilsJre.readFromFile(_get_1);
  }
  
  public void execute(final List<FileOperation> operations, final FileItem withFolder, final Metadata withMetadata) {
    final FileOperationContext ctx = new FileOperationContext() {
      public FileItem folder() {
        return withFolder;
      }
      
      public Metadata metadata() {
        return withMetadata;
      }
    };
    for (final FileOperation op : operations) {
      op.apply(ctx);
    }
  }
  
  /**
   * based on http://grepcode.com/file_/repository.springsource.com/org.apache.activemq/com.springsource.org.apache.kahadb/5.3.0/org/apache/kahadb/util/IOHelper.java/?v=source
   * 
   * Converts any string into a string that is safe to use as a file name.
   * The result will only include ascii characters and numbers, and the "-","_", and "." characters.
   * 
   * @param name
   * @param dirSeparators
   * @param maxFileLength
   * @return
   */
  public String toFileSystemSafeName(final String name, final boolean dirSeparators, final int maxFileLength) {
    final int size = name.length();
    final StringBuffer rc = new StringBuffer((size * 2));
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final char c = name.charAt((i).intValue());
        boolean valid = ((c >= 'a') && (c <= 'z'));
        valid = (valid || ((c >= 'A') && (c <= 'Z')));
        valid = (valid || ((c >= '0') && (c <= '9')));
        boolean _or = false;
        boolean _or_1 = false;
        boolean _or_2 = false;
        boolean _or_3 = false;
        boolean _or_4 = false;
        if (valid) {
          _or_4 = true;
        } else {
          char _charAt = "_".charAt(0);
          boolean _equals = (c == _charAt);
          _or_4 = _equals;
        }
        if (_or_4) {
          _or_3 = true;
        } else {
          char _charAt_1 = "-".charAt(0);
          boolean _equals_1 = (c == _charAt_1);
          _or_3 = _equals_1;
        }
        if (_or_3) {
          _or_2 = true;
        } else {
          char _charAt_2 = ".".charAt(0);
          boolean _equals_2 = (c == _charAt_2);
          _or_2 = _equals_2;
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          char _charAt_3 = "#".charAt(0);
          boolean _equals_3 = (c == _charAt_3);
          _or_1 = _equals_3;
        }
        if (_or_1) {
          _or = true;
        } else {
          boolean _and = false;
          if (!dirSeparators) {
            _and = false;
          } else {
            boolean _or_5 = false;
            char _charAt_4 = "/".charAt(0);
            boolean _equals_4 = (c == _charAt_4);
            if (_equals_4) {
              _or_5 = true;
            } else {
              char _charAt_5 = "\\".charAt(0);
              boolean _equals_5 = (c == _charAt_5);
              _or_5 = _equals_5;
            }
            _and = _or_5;
          }
          _or = _and;
        }
        valid = _or;
        boolean _or_6 = false;
        if (valid) {
          _or_6 = true;
        } else {
          char _charAt_6 = " ".charAt(0);
          boolean _equals_6 = (c == _charAt_6);
          _or_6 = _equals_6;
        }
        valid = _or_6;
        if (valid) {
          rc.append(c);
        } else {
          rc.append("_");
        }
      }
    }
    String result = rc.toString();
    int _length = result.length();
    boolean _greaterThan = (_length > maxFileLength);
    if (_greaterThan) {
      String _substring = result.substring(0, (maxFileLength - 1));
      result = _substring;
    }
    return result;
  }
}
