package com.appjangle.filesync;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.SyncParams;
import com.appjangle.filesync.SynchronizationSettings;
import com.appjangle.filesync.SynchronizationState;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.SyncFolder;
import com.appjangle.filesync.internal.engine.convert.ConverterCollection;
import com.appjangle.filesync.internal.engine.convert.FileToTextNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNode;
import com.appjangle.filesync.internal.engine.convert.FolderToNothing;
import com.appjangle.filesync.internal.engine.convert.NodeToNothing;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.Function;
import de.mxro.fn.Success;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.nodes.Token;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.io.File;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class FileSync {
  public static void syncSingleFolder(final SyncParams params, final ValueCallback<Success> cb) {
    SyncFolder _syncFolder = new SyncFolder(params);
    _syncFolder.doIt(cb);
  }
  
  public static SyncParams defaultSyncParams() {
    final SyncParams params = new SyncParams();
    ConverterCollection _createDefaultConverter = FileSync.createDefaultConverter();
    params.setConverter(_createDefaultConverter);
    SynchronizationSettings _synchronizationSettings = new SynchronizationSettings();
    params.setSettings(_synchronizationSettings);
    params.setState(new SynchronizationState() {
    });
    return params;
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final File folder, final Node node, final ValueCallback<Success> cb) {
    FileItem _wrap = FilesJre.wrap(folder);
    FileSync.syncSingleFolder(_wrap, node, cb);
  }
  
  /**
   * <p>Synchronized the contents of a folder and a node without synchronizing sub-folders.
   */
  public static void syncSingleFolder(final FileItem folder, final Node node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.syncSingleFolder(params, cb);
  }
  
  public static void sync(final SyncParams params, final ValueCallback<Success> cb) {
    final Closure<Success> _function = new Closure<Success>() {
      public void apply(final Success it) {
        FileItem _folder = params.getFolder();
        List<FileItem> _children = _folder.getChildren();
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
        final Closure2<FileItem, ValueCallback<Success>> _function_1 = new Closure2<FileItem, ValueCallback<Success>>() {
          public void apply(final FileItem childFolder, final ValueCallback<Success> itmcb) {
            FileItem _folder = params.getFolder();
            final Metadata metadata = FileSync.fileUtils.loadMetadata(_folder);
            String _name = childFolder.getName();
            final ItemMetadata itmmetadata = metadata.get(_name);
            Node _node = params.getNode();
            Session _session = _node.session();
            String _uri = itmmetadata.uri();
            final Link qry = _session.link(_uri);
            final ExceptionListener _function = new ExceptionListener() {
              public void onFailure(final ExceptionResult er) {
                Throwable _exception = er.exception();
                itmcb.onFailure(_exception);
              }
            };
            qry.catchExceptions(_function);
            final Closure<Node> _function_1 = new Closure<Node>() {
              public void apply(final Node childNode) {
                final SyncParams childParams = new SyncParams(params);
                childParams.setFolder(childFolder);
                childParams.setNode(childNode);
                FileSync.sync(childParams, itmcb);
              }
            };
            qry.get(_function_1);
          }
        };
        final Closure<List<Success>> _function_2 = new Closure<List<Success>>() {
          public void apply(final List<Success> it) {
            cb.onSuccess(Success.INSTANCE);
          }
        };
        ValueCallback<List<Success>> _embed = Async.<List<Success>>embed(cb, _function_2);
        Async.<FileItem, Success>forEach(_list, _function_1, _embed);
      }
    };
    ValueCallback<Success> _embed = Async.<Success>embed(cb, _function);
    FileSync.syncSingleFolder(params, _embed);
  }
  
  /**
   * <p>Synchronized the contents of the specified folder with the specified nodes and does the same for all sub-folders and child nodes.
   */
  public static void sync(final FileItem folder, final Node node, final ValueCallback<Success> cb) {
    final SyncParams params = FileSync.defaultSyncParams();
    params.setFolder(folder);
    params.setNode(node);
    FileSync.sync(params, cb);
  }
  
  public static ConverterCollection createDefaultConverter() {
    ConverterCollection _xblockexpression = null;
    {
      final ConverterCollection coll = new ConverterCollection();
      FileToTextNode _fileToTextNode = new FileToTextNode();
      coll.addConverter(_fileToTextNode);
      final Closure2<Node, ValueCallback<Boolean>> _function = new Closure2<Node, ValueCallback<Boolean>>() {
        public void apply(final Node node, final ValueCallback<Boolean> cb) {
          Object _value = node.value();
          cb.onSuccess(Boolean.valueOf((_value instanceof Token)));
        }
      };
      NodeToNothing _nodeToNothing = new NodeToNothing(_function);
      coll.addConverter(_nodeToNothing);
      final Function<FileItem, Boolean> _function_1 = new Function<FileItem, Boolean>() {
        public Boolean apply(final FileItem file) {
          boolean _or = false;
          String _name = file.getName();
          boolean _startsWith = _name.startsWith(".");
          if (_startsWith) {
            _or = true;
          } else {
            boolean _visible = file.getVisible();
            boolean _not = (!_visible);
            _or = _not;
          }
          return Boolean.valueOf(_or);
        }
      };
      FolderToNothing _folderToNothing = new FolderToNothing(_function_1);
      coll.addConverter(_folderToNothing);
      FolderToNode _folderToNode = new FolderToNode();
      coll.addConverter(_folderToNode);
      _xblockexpression = coll;
    }
    return _xblockexpression;
  }
  
  @Extension
  private static FileUtils fileUtils = new FileUtils();
}
