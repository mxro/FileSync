package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.internal.engine.N;
import com.google.common.base.Objects;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
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
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(N.LABEL()));
  
  private final Map<String, String> textValueExtensions = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of(N.HTML_VALUE(), ".html"), Pair.<String, String>of("1", ".type"), Pair.<String, String>of("2", ".css"), Pair.<String, String>of("3", ".js"), Pair.<String, String>of(N.COFFEESCRIPT(), ".coffee")));
  
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
      final String ext = source.getExtension();
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Link _HTML_VALUE = this.n.HTML_VALUE(session);
        toNode.appendSafe(_HTML_VALUE);
        Link _TEMPLATE = this.n.TEMPLATE(session);
        toNode.appendSafe(_TEMPLATE);
        Query _appendSafe = toNode.appendSafe("https://docs.google.com/drawings/d/1AOVTMvvcdKAh3Y8aZP4kTx_c6oTMuNKkSdmgSAnPnr0/pub?w=70&h=70", "./.icon");
        Link _ICON = this.n.ICON(session);
        _appendSafe.appendSafe(_ICON);
      }
      String _TEXT_VALUE = N.TEXT_VALUE();
      Link _link = session.link(_TEXT_VALUE);
      _xblockexpression = toNode.appendSafe(_link);
    }
    return _xblockexpression;
  }
  
  public final static Object NO_VALUE = new Object();
  
  public void getFileName(final Node forNode, final FileItem inFolder, final String fileExtension, final ValueCallback<String> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String fileNameFromNode) {
        String fileName = (fileNameFromNode + fileExtension);
        int idx = 1;
        while (inFolder.get(fileName).exists()) {
          {
            fileName = ((fileNameFromNode + Integer.valueOf(idx)) + fileExtension);
            idx++;
          }
        }
        cb.onSuccess(fileName);
      }
    };
    ValueCallback<String> _embed = Async.<String>embed(cb, _function);
    this.getFileName(forNode, _embed);
  }
  
  public void getFileName(final Node fromNode, final ValueCallback<String> cb) {
    int _size = this.labelTypes.size();
    final Closure<List<Object>> _function = new Closure<List<Object>>() {
      public void apply(final List<Object> res) {
        for (final Object item : res) {
          if ((item instanceof String)) {
            cb.onSuccess(((String)item));
            return;
          }
        }
        String _uri = fromNode.uri();
        String _nameFromUri = ConvertUtils.getNameFromUri(_uri);
        cb.onSuccess(_nameFromUri);
      }
    };
    ValueCallback<List<Object>> _embed = Async.<List<Object>>embed(cb, _function);
    final Aggregator<Object> cbs = Async.<Object>collect(_size, _embed);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String labelType) {
        Session _session = fromNode.session();
        Link _link = _session.link(labelType);
        final Query qry = fromNode.select(_link);
        final ValueCallback<Object> itmcb = cbs.createCallback();
        final UndefinedListener _function = new UndefinedListener() {
          public void onUndefined(final UndefinedResult it) {
            itmcb.onSuccess(ConvertUtils.NO_VALUE);
          }
        };
        qry.catchUndefined(_function);
        final ExceptionListener _function_1 = new ExceptionListener() {
          public void onFailure(final ExceptionResult er) {
            Throwable _exception = er.exception();
            itmcb.onFailure(_exception);
          }
        };
        qry.catchExceptions(_function_1);
        final Closure<Node> _function_2 = new Closure<Node>() {
          public void apply(final Node label) {
            Object _value = label.value();
            itmcb.onSuccess(_value);
          }
        };
        qry.get(_function_2);
      }
    };
    this.labelTypes.forEach(_function_1);
  }
  
  public static String getNameFromUri(final String uri) {
    int _lastIndexOf = uri.lastIndexOf("/");
    int _plus = (_lastIndexOf + 1);
    return uri.substring(_plus);
  }
  
  @Extension
  private N n = new N();
}
