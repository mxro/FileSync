package com.appjangle.filesync.engine.convert;

import com.appjangle.filesync.Convert;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.engine.metadata.FileItemMetadata;
import com.appjangle.filesync.engine.metadata.NodesMetadata;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Success;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.promise.NextwebPromise;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class FileToTextNode implements Convert {
  public String fileNameToAddress(final String fileName) {
    return fileName;
  }
  
  public void update(final NodesMetadata metadata, final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from Query to NextwebPromise<?>[]");
  }
  
  public void createNodes(final NodesMetadata metadata, final FileItem source, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
  }
  
  public void deleteNodes(final NodesMetadata metadata, final FileItemMetadata cachedFile, final Node parent, final ValueCallback<List<NetworkOperation>> cb) {
    final String address = cachedFile.uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<NextwebPromise<?>> apply(final NetworkOperationContext ctx) {
        Node _node = ctx.node();
        Session _session = ctx.session();
        Link _link = _session.link(address);
        NextwebPromise<Success> _removeSafe = _node.removeSafe(_link);
        return CollectionLiterals.<NextwebPromise<?>>newArrayList(_removeSafe);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
}
