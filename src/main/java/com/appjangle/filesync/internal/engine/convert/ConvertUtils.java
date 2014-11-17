package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.internal.engine.N;
import com.google.common.base.Objects;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.LinkList;
import io.nextweb.LinkListQuery;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(N.LABEL()));
  
  private final Map<String, String> fileExtensions = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of(N.HTML_VALUE(), ".html"), Pair.<String, String>of("1", ".type"), Pair.<String, String>of("2", ".css"), Pair.<String, String>of("3", ".js")));
  
  public void getFileExtension(final Node forNode, final ValueCallback<String> cb) {
    final LinkListQuery qry = forNode.selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      public void apply(final LinkList links) {
        Set<Map.Entry<String, String>> _entrySet = ConvertUtils.this.fileExtensions.entrySet();
        for (final Map.Entry<String, String> mapping : _entrySet) {
          String _key = mapping.getKey();
          boolean _contains = links.contains(_key);
          if (_contains) {
            String _value = mapping.getValue();
            cb.onSuccess(_value);
            return;
          }
        }
      }
    };
    qry.get(_function_1);
  }
  
  public Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label, "./.label");
    Session _session = toNode.session();
    Link _LABEL = this.n.LABEL(_session);
    return _appendSafe.appendSafe(_LABEL);
  }
  
  public Query appendTypes(final Query toNode, final FileItem source) {
    Query _xblockexpression = null;
    {
      final String ext = source.getExtension();
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Session _session = toNode.session();
        String _HTML_VALUE = N.HTML_VALUE();
        Link _link = _session.link(_HTML_VALUE);
        toNode.appendSafe(_link);
        Session _session_1 = toNode.session();
        String _TEMPLATE = N.TEMPLATE();
        Link _link_1 = _session_1.link(_TEMPLATE);
        toNode.appendSafe(_link_1);
      }
      Session _session_2 = toNode.session();
      String _TEXT_VALUE = N.TEXT_VALUE();
      Link _link_2 = _session_2.link(_TEXT_VALUE);
      _xblockexpression = toNode.appendSafe(_link_2);
    }
    return _xblockexpression;
  }
  
  public final static Object NO_VALUE = new Object();
  
  public void getFileName(final Node forNode, final FileItem inFolder, final String fileExtension, final ValueCallback<String> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConvertUtils"
      + "\nType mismatch: cannot convert from int to Iterable<?>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void getFileName(final Node fromNode, final ValueCallback<String> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConvertUtils"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public static String getNameFromUri(final String uri) {
    int _lastIndexOf = uri.lastIndexOf("/");
    int _plus = (_lastIndexOf + 1);
    return uri.substring(_plus);
  }
  
  @Extension
  private N n = new N();
}
