package com.appjangle.filesync;

import com.appjangle.filesync.internal.engine.SyncFolder;
import com.appjangle.filesync.internal.engine.convert.ConverterCollection;
import com.appjangle.filesync.internal.engine.convert.FileToTextNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNode;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Success;
import io.nextweb.Node;

@SuppressWarnings("all")
public class FileSync {
  public static void sync(final FileItem folder, final Node node, final ValueCallback<Success> cb) {
    ConverterCollection _createDefaultConverter = FileSync.createDefaultConverter();
    SyncFolder _syncFolder = new SyncFolder(folder, node, _createDefaultConverter);
    _syncFolder.doIt(cb);
  }
  
  public static ConverterCollection createDefaultConverter() {
    ConverterCollection _xblockexpression = null;
    {
      final ConverterCollection coll = new ConverterCollection();
      FileToTextNode _fileToTextNode = new FileToTextNode();
      coll.addConverter(_fileToTextNode);
      FolderToNode _folderToNode = new FolderToNode();
      coll.addConverter(_folderToNode);
      _xblockexpression = coll;
    }
    return _xblockexpression;
  }
}
