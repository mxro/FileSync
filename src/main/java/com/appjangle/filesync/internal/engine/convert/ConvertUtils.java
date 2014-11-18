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
import mx.gwtutils.MxroGWTUtils;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(N.LABEL()));
  
  private final Map<String, String> textValueExtensions = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of(N.HTML_VALUE(), ".html"), Pair.<String, String>of(N.TYPE(), ".type"), Pair.<String, String>of(N.CSS(), ".css"), Pair.<String, String>of(N.JAVASCRIPT(), ".js"), Pair.<String, String>of(N.COFFEESCRIPT(), ".coffee")));
  
  public boolean isTextValue(final String fileName) {
    boolean _xblockexpression = false;
    {
      final String ext = MxroGWTUtils.getExtension(fileName);
      _xblockexpression = this.textValueExtensions.containsValue(("." + ext));
    }
    return _xblockexpression;
  }
  
  public boolean isTextType(final Link link) {
    Set<String> _keySet = this.textValueExtensions.keySet();
    String _uri = link.uri();
    return _keySet.contains(_uri);
  }
  
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
        Set<Map.Entry<String, String>> _entrySet = ConvertUtils.this.textValueExtensions.entrySet();
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
  
  public Query appendTypesAndIcon(final Query toNode, final FileItem source) {
    Query _xblockexpression = null;
    {
      final Session session = toNode.session();
      String ext = source.getExtension();
      ext = ("." + ext);
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Link _HTML_VALUE = this.n.HTML_VALUE(session);
        toNode.appendSafe(_HTML_VALUE);
        Link _TEMPLATE = this.n.TEMPLATE(session);
        toNode.appendSafe(_TEMPLATE);
        Query _appendSafe = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon");
        Link _ICON = this.n.ICON(session);
        _appendSafe.appendSafe(_ICON);
      } else {
        boolean _equals_1 = Objects.equal(ext, ".js");
        if (_equals_1) {
          Link _JAVASCRIPT = this.n.JAVASCRIPT(session);
          toNode.appendSafe(_JAVASCRIPT);
          Link _TEMPLATE_1 = this.n.TEMPLATE(session);
          toNode.appendSafe(_TEMPLATE_1);
          Query _appendSafe_1 = toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon");
          Link _ICON_1 = this.n.ICON(session);
          _appendSafe_1.appendSafe(_ICON_1);
        } else {
          boolean _equals_2 = Objects.equal(ext, ".coffee");
          if (_equals_2) {
            Link _COFFEESCRIPT = this.n.COFFEESCRIPT(session);
            toNode.appendSafe(_COFFEESCRIPT);
            Link _TEMPLATE_2 = this.n.TEMPLATE(session);
            toNode.appendSafe(_TEMPLATE_2);
            Query _appendSafe_2 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon");
            Link _ICON_2 = this.n.ICON(session);
            _appendSafe_2.appendSafe(_ICON_2);
          } else {
            boolean _equals_3 = Objects.equal(ext, ".css");
            if (_equals_3) {
              Link _CSS = this.n.CSS(session);
              toNode.appendSafe(_CSS);
              Link _TEMPLATE_3 = this.n.TEMPLATE(session);
              toNode.appendSafe(_TEMPLATE_3);
              Query _appendSafe_3 = toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon");
              Link _ICON_3 = this.n.ICON(session);
              _appendSafe_3.appendSafe(_ICON_3);
            } else {
              boolean _equals_4 = Objects.equal(ext, ".type");
              if (_equals_4) {
                Link _TYPE = this.n.TYPE(session);
                toNode.appendSafe(_TYPE);
                Query _appendSafe_4 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon");
                Link _ICON_4 = this.n.ICON(session);
                _appendSafe_4.appendSafe(_ICON_4);
                Query _appendSafe_5 = toNode.appendSafe("");
                Link _link = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9");
                _appendSafe_5.appendSafe(_link);
              }
            }
          }
        }
      }
      String _TEXT_VALUE = N.TEXT_VALUE();
      Link _link_1 = session.link(_TEXT_VALUE);
      _xblockexpression = toNode.appendSafe(_link_1);
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
      + "\nThe method collect is undefined for the type ConvertUtils"
      + "\nThe method embed is undefined for the type ConvertUtils"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ncreateCallback cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonFailure cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public static String getNameFromUri(final String uri) {
    int _lastIndexOf = uri.lastIndexOf("/");
    int _plus = (_lastIndexOf + 1);
    return uri.substring(_plus);
  }
  
  @Extension
  private N n = new N();
}
