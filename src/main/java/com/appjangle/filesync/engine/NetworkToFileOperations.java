package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import io.nextweb.ListQuery;
import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.List;

/**
 * Determines operations to be performed on local files based on remote changes made in the cloud.
 */
@SuppressWarnings("all")
public class NetworkToFileOperations {
  private final Node node;
  
  private final FileItem folder;
  
  private final Metadata metadata;
  
  private final Converter converter;
  
  public NetworkToFileOperations(final Node node, final FileItem folder, final Metadata metadata, final Converter converter) {
    this.node = node;
    this.folder = folder;
    this.metadata = metadata;
    this.converter = converter;
  }
  
  public NodeList determineOps(final ValueCallback<List<FileOperation>> cb) {
    NodeList _xblockexpression = null;
    {
      final ListQuery qry = this.node.selectAll();
      final ExceptionListener _function = new ExceptionListener() {
        public void onFailure(final ExceptionResult er) {
          Throwable _exception = er.exception();
          cb.onFailure(_exception);
        }
      };
      qry.catchExceptions(_function);
      _xblockexpression = qry.get();
    }
    return _xblockexpression;
  }
}
