package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.FileOperationContext;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      final String name = source.getName();
      _xblockexpression = name.endsWith(".html");
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    final List<String> textNodeTypes = Collections.<String>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<String>newArrayList("https://admin1.linnk.it/types/v01/isHtmlValue"));
    int _size = textNodeTypes.size();
    final Closure<List<Boolean>> _function = new Closure<List<Boolean>>() {
      public void apply(final List<Boolean> res) {
        boolean _contains = res.contains(Boolean.valueOf(true));
        cb.onSuccess(Boolean.valueOf(_contains));
      }
    };
    ValueCallback<List<Boolean>> _embed = Async.<List<Boolean>>embed(cb, _function);
    final Aggregator<Boolean> cbs = Async.<Boolean>collect(_size, _embed);
    for (final String textType : textNodeTypes) {
      {
        final ValueCallback<Boolean> itmcb = cbs.createCallback();
        Session _session = node.session();
        Link _link = _session.link(textType);
        final Query qry = node.select(_link);
        final ExceptionListener _function_1 = new ExceptionListener() {
          public void onFailure(final ExceptionResult er) {
            Throwable _exception = er.exception();
            itmcb.onFailure(_exception);
          }
        };
        qry.catchExceptions(_function_1);
        final UndefinedListener _function_2 = new UndefinedListener() {
          public void onUndefined(final UndefinedResult it) {
            itmcb.onSuccess(Boolean.valueOf(false));
          }
        };
        qry.catchUndefined(_function_2);
        final Closure<Node> _function_3 = new Closure<Node>() {
          public void apply(final Node it) {
            itmcb.onSuccess(Boolean.valueOf(true));
          }
        };
        qry.get(_function_3);
      }
    }
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\n+ cannot be resolved."
      + "\nThe method newArrayList is undefined for the type FileToTextNode"
      + "\nThe method appendLabel is undefined for the type FileToTextNode"
      + "\nThe method appendTypes is undefined for the type FileToTextNode");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method newArrayList is undefined for the type FileToTextNode");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method newArrayList is undefined for the type FileToTextNode");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method getFileExtension is undefined for the type FileToTextNode"
      + "\nThe method getFileName is undefined for the type FileToTextNode"
      + "\nThe method toFileSystemSafeName is undefined for the type FileToTextNode");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\n!= cannot be resolved.");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final String fileName = item.name();
    final LinkedList<FileOperation> ops = new LinkedList<FileOperation>();
    final FileOperation _function = new FileOperation() {
      public void apply(final FileOperationContext ctx) {
        FileItem _folder = ctx.folder();
        _folder.deleteFile(fileName);
        Metadata _metadata = ctx.metadata();
        _metadata.remove(fileName);
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  private ConvertUtils cutils = new ConvertUtils();
  
  private FileUtils futils = new FileUtils();
}
