package com.appjangle.filesync.engine.convert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import io.nextweb.Link;
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
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class ConvertUtils {
  private final Map<String, String> extensions = new Function0<Map<String, String>>() {
    public Map<String, String> apply() {
      Map<String, String> _xsetliteral = null;
      Map<String, String> _tempMap = Maps.<String, String>newHashMap();
      _tempMap.put("", ".html");
      _xsetliteral = Collections.<String, String>unmodifiableMap(_tempMap);
      return _xsetliteral;
    }
  }.apply();
  
  public void getFileExtension(final Node forNode, final ValueCallback<String> cb) {
    int _size = this.extensions.size();
    final Closure<List<Object>> _function = new Closure<List<Object>>() {
      public void apply(final List<Object> res) {
      }
    };
    ValueCallback<List<Object>> _embed = Async.<List<Object>>embed(cb, _function);
    final Aggregator<Object> cbs = Async.<Object>collect(_size, _embed);
    final LinkListQuery qry = forNode.selectAllLinks();
    final ExceptionListener _function_1 = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function_1);
    Set<Map.Entry<String, String>> _entrySet = this.extensions.entrySet();
    for (final Map.Entry<String, String> mapping : _entrySet) {
    }
  }
  
  private final List<String> labelTypes = Collections.<String>unmodifiableList(Lists.<String>newArrayList("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"));
  
  public Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label);
    Session _session = toNode.session();
    String _get = this.labelTypes.get(0);
    Link _link = _session.link(_get);
    return _appendSafe.appendSafe(_link, "./label");
  }
  
  private final static Object NO_LABEL = new Object();
  
  public void getFileName(final Node forNode, final FileItem inFolder, final String fileExtension, final ValueCallback<String> cb) {
    final Closure<String> _function = new Closure<String>() {
      public void apply(final String fileNameFromNode) {
        String fileName = (fileNameFromNode + fileExtension);
        int idx = 1;
        FileItem _child = inFolder.getChild(fileName);
        boolean _exists = _child.exists();
        boolean _while = _exists;
        while (_while) {
          {
            fileName = ((fileNameFromNode + Integer.valueOf(idx)) + fileExtension);
            idx++;
          }
          FileItem _child_1 = inFolder.getChild(fileName);
          boolean _exists_1 = _child_1.exists();
          _while = _exists_1;
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
            itmcb.onSuccess(ConvertUtils.NO_LABEL);
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
}
