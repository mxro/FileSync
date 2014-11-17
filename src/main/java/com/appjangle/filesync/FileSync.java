package com.appjangle.filesync;

import com.appjangle.filesync.internal.engine.SyncFolder;
import com.appjangle.filesync.internal.engine.convert.ConverterCollection;
import com.appjangle.filesync.internal.engine.convert.FileToTextNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNode;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.Success;
import io.nextweb.Node;
import java.io.File;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class FileSync {
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final File folder, final Node node, final ValueCallback<Success> cb) {
    FileItem _wrap = FilesJre.wrap(folder);
    ConverterCollection _createDefaultConverter = FileSync.createDefaultConverter();
    SyncFolder _syncFolder = new SyncFolder(_wrap, node, _createDefaultConverter);
    _syncFolder.doIt(cb);
  }
  
  /**
   * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
   */
  public static void sync(final File folder, final Node node, final ValueCallback<Success> cb) {
    final Closure<Success> _function = new Closure<Success>() {
      public void apply(final Success it) {
        FileItem _wrap = FilesJre.wrap(folder);
        List<FileItem> _children = _wrap.getChildren();
        final Function1<FileItem, Boolean> _function = new Function1<FileItem, Boolean>() {
          public Boolean apply(final FileItem it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isDirectory = it.isDirectory();
            if (!_isDirectory) {
              _and_1 = false;
            } else {
              boolean _visible = it.getVisible();
              _and_1 = _visible;
            }
            if (!_and_1) {
              _and = false;
            } else {
              String _name = it.getName();
              boolean _startsWith = _name.startsWith(".");
              boolean _not = (!_startsWith);
              _and = _not;
            }
            return Boolean.valueOf(_and);
          }
        };
        final Iterable<FileItem> toSync = IterableExtensions.<FileItem>filter(_children, _function);
        List<FileItem> _list = IterableExtensions.<FileItem>toList(toSync);
        final Closure2<FileItem, ValueCallback<Object>> _function_1 = new Closure2<FileItem, ValueCallback<Object>>() {
          public void apply(final FileItem item, final ValueCallback<Object> itmcb) {
          }
        };
        final Closure<List<Object>> _function_2 = new Closure<List<Object>>() {
          public void apply(final List<Object> it) {
            cb.onSuccess(Success.INSTANCE);
          }
        };
        ValueCallback<List<Object>> _embed = Async.<List<Object>>embed(cb, _function_2);
        Async.<FileItem, Object>forEach(_list, _function_1, _embed);
      }
    };
    ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
    FileSync.syncSingleFolder(folder, node, _embed);
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
