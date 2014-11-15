package com.appjangle.filesync.internal.engine;

import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl;
import com.appjangle.filesync.internal.engine.metadata.MetadataUtilsJre;
import com.google.common.base.Objects;
import de.mxro.file.FileItem;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class FileUtils {
  public void saveForFolder(final Metadata metadata, final FileItem forFolder) {
    FileItem _child = forFolder.getChild(".filesync-meta");
    FileItem _child_1 = _child.getChild("nodes.xml");
    MetadataUtilsJre.saveToFile(metadata, _child_1);
  }
  
  public boolean hasMetadata(final FileItem forFolder) {
    FileItem _assertFolder = forFolder.assertFolder(".filesync-meta");
    FileItem _child = _assertFolder.getChild("nodes.xml");
    return _child.exists();
  }
  
  public Metadata assertMetadata(final FileItem forFolder) {
    Metadata _xblockexpression = null;
    {
      final FileItem metadataFolder = forFolder.assertFolder(".filesync-meta");
      metadataFolder.setVisible(false);
      FileItem _child = metadataFolder.getChild("nodes.xml");
      boolean _exists = _child.exists();
      boolean _not = (!_exists);
      if (_not) {
        final FileItem metadataFile = metadataFolder.createFile("nodes.xml");
        final MetadataImpl metadata = new MetadataImpl();
        MetadataUtilsJre.saveToFile(metadata, metadataFile);
        return metadata;
      }
      FileItem _child_1 = metadataFolder.getChild("nodes.xml");
      _xblockexpression = MetadataUtilsJre.readFromFile(_child_1);
    }
    return _xblockexpression;
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
          boolean _equals = Objects.equal(Character.valueOf(c), "_");
          _or_4 = _equals;
        }
        if (_or_4) {
          _or_3 = true;
        } else {
          boolean _equals_1 = Objects.equal(Character.valueOf(c), "-");
          _or_3 = _equals_1;
        }
        if (_or_3) {
          _or_2 = true;
        } else {
          boolean _equals_2 = Objects.equal(Character.valueOf(c), ".");
          _or_2 = _equals_2;
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          boolean _equals_3 = Objects.equal(Character.valueOf(c), "#");
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
            boolean _equals_4 = Objects.equal(Character.valueOf(c), "/");
            if (_equals_4) {
              _or_5 = true;
            } else {
              boolean _equals_5 = Objects.equal(Character.valueOf(c), "\\");
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
          boolean _equals_6 = Objects.equal(Character.valueOf(c), " ");
          _or_6 = _equals_6;
        }
        valid = _or_6;
        boolean _or_7 = false;
        if (valid) {
          _or_7 = true;
        } else {
          char _charAt = " ".charAt(0);
          boolean _equals_7 = (c == _charAt);
          _or_7 = _equals_7;
        }
        valid = _or_7;
        InputOutput.<String>println(((("[" + Character.valueOf(c)) + "]=>") + Boolean.valueOf(valid)));
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
      int _length_1 = result.length();
      int _minus = (_length_1 - maxFileLength);
      int _length_2 = result.length();
      String _substring = result.substring(_minus, _length_2);
      result = _substring;
    }
    return result;
  }
}
