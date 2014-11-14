package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import com.google.common.collect.Lists;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gwtutils.MxroGWTUtils;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FileToTextNode implements Converter {
  @Extension
  private ConvertUtils utils = new ConvertUtils();
  
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      final String name = source.getName();
      _xblockexpression = name.endsWith(".txt");
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    final List<String> textNodeTypes = Collections.<String>unmodifiableList(Lists.<String>newArrayList("https://admin1.linnk.it/types/v01/isHtmlValue"));
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
  
  public void createNodes(final /* Metadata */Object metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    String _name = source.getName();
    final String nameWithoutExtension = MxroGWTUtils.removeExtension(_name);
    final String simpleName = MxroGWTUtils.getSimpleName(nameWithoutExtension);
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public List<Deferred<?>> apply(final NetworkOperationContext ctx) {
        ArrayList<Deferred<?>> _xblockexpression = null;
        {
          Node _parent = ctx.parent();
          String _text = source.getText();
          final Query baseNode = _parent.appendSafe(_text, ("./" + simpleName));
          Query _appendLabel = FileToTextNode.this.utils.appendLabel(baseNode, nameWithoutExtension);
          Query _appendTypes = FileToTextNode.this.utils.appendTypes(baseNode, source);
          _xblockexpression = CollectionLiterals.<Deferred<?>>newArrayList(baseNode, _appendLabel, _appendTypes);
        }
        return _xblockexpression;
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public void update(final /* Metadata */Object metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nAmbiguous feature call.\nThe methods\n\tlink(Link) in SessionOperations,\n\tlink(Node) in SessionOperations and\n\tlink(String) in SessionOperations\nall match."
      + "\nget cannot be resolved"
      + "\nuri cannot be resolved");
  }
  
  public void deleteNodes(final /* Metadata */Object metadata, final /* ItemMetadata */Object cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nAmbiguous feature call.\nThe methods\n\tlink(Link) in SessionOperations,\n\tlink(Node) in SessionOperations and\n\tlink(String) in SessionOperations\nall match."
      + "\nuri cannot be resolved");
  }
  
  public void createFiles(final FileItem folder, final /* Metadata */Object metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nItemMetadata cannot be resolved."
      + "\nThe method name() of type new Object(){} must override a superclass method."
      + "\nThe method lastModified() of type new Object(){} must override a superclass method."
      + "\nThe method uri() of type new Object(){} must override a superclass method."
      + "\nThe method hash() of type new Object(){} must override a superclass method."
      + "\nadd cannot be resolved");
  }
  
  public void updateFiles(final FileItem folder, final /* Metadata */Object metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nItemMetadata cannot be resolved."
      + "\nThe method name() of type new Object(){} must override a superclass method."
      + "\nThe method lastModified() of type new Object(){} must override a superclass method."
      + "\nThe method uri() of type new Object(){} must override a superclass method."
      + "\nThe method hash() of type new Object(){} must override a superclass method."
      + "\nget cannot be resolved"
      + "\nname cannot be resolved"
      + "\nupdate cannot be resolved");
  }
  
  public void removeFiles(final FileItem folder, final /* Metadata */Object metadata, final /* ItemMetadata */Object item, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nname cannot be resolved"
      + "\nremove cannot be resolved");
  }
}
