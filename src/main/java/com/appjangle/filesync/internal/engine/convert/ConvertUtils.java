package com.appjangle.filesync.internal.engine.convert;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(Lists.<String>newArrayList("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"));
  
  private final Map<String, String> fileExtensions = new Function0<Map<String, String>>() {
    public Map<String, String> apply() {
      Map<String, String> _xsetliteral = null;
      Map<String, String> _tempMap = Maps.<String, String>newHashMap();
      _tempMap.put("https://admin1.linnk.it/types/v01/isHtmlValue", ".html");
      _tempMap.put("", "");
      _xsetliteral = Collections.<String, String>unmodifiableMap(_tempMap);
      return _xsetliteral;
    }
  }.apply();
  
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
    Query _appendSafe = toNode.appendSafe(label);
    Session _session = toNode.session();
    String _get = this.labelTypes.get(0);
    Link _link = _session.link(_get);
    return _appendSafe.appendSafe(_link, "./label");
  }
  
  public Query appendTypes(final Query toNode, final FileItem source) {
    Query _xblockexpression = null;
    {
      final String ext = source.getExtension();
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Session _session = toNode.session();
        Link _link = _session.link("https://admin1.linnk.it/types/v01/isHtmlValue");
        toNode.appendSafe(_link);
        Session _session_1 = toNode.session();
        Link _link_1 = _session_1.link("https://u1.linnk.it/6wbnoq/Types/aTemplate");
        toNode.appendSafe(_link_1);
      }
      Session _session_2 = toNode.session();
      Link _link_2 = _session_2.link("https://u1.linnk.it/6wbnoq/Types/aTextValue");
      _xblockexpression = toNode.appendSafe(_link_2);
    }
    return _xblockexpression;
  }
  
  private final static Object NO_VALUE = new Object();
  
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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field com is undefined for the type ConvertUtils"
      + "\nappjangle cannot be resolved"
      + "\nfilesync cannot be resolved"
      + "\nengine cannot be resolved"
      + "\nconvert cannot be resolved"
      + "\nConvertUtils cannot be resolved"
      + "\nNO_VALUE cannot be resolved");
  }
  
  public static String getNameFromUri(final String uri) {
    int _lastIndexOf = uri.lastIndexOf("/");
    int _plus = (_lastIndexOf + 1);
    return uri.substring(_plus);
  }
}
