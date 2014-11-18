package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.N;
import com.google.common.base.Objects;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Success;
import io.nextweb.Link;
import io.nextweb.LinkList;
import io.nextweb.LinkListQuery;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.Deferred;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import io.nextweb.utils.data.NextwebDataExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
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
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    final String address = cachedFile.uri();
    final LinkedList<NetworkOperation> ops = new LinkedList<NetworkOperation>();
    final NetworkOperation _function = new NetworkOperation() {
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<Deferred<?>>> opscb) {
        String _name = cachedFile.name();
        metadata.remove(_name);
        Session _session = ctx.session();
        final Link nodeToBeRemoved = _session.link(address);
        final Node parent = ctx.parent();
        final ArrayList<Deferred<?>> list = new ArrayList<Deferred<?>>();
        Session _session_1 = parent.session();
        Link _link = _session_1.link(parent);
        boolean _hasDirectChild = ConvertUtils.this.ext.hasDirectChild(_link, nodeToBeRemoved);
        if (_hasDirectChild) {
          final Closure<List<NextwebPromise<Success>>> _function = new Closure<List<NextwebPromise<Success>>>() {
            public void apply(final List<NextwebPromise<Success>> res) {
              list.addAll(res);
              opscb.onSuccess(list);
            }
          };
          ValueCallback<List<NextwebPromise<Success>>> _embed = Async.<List<NextwebPromise<Success>>>embed(opscb, _function);
          ConvertUtils.this.ext.removeSafeRecursive(parent, nodeToBeRemoved, _embed);
        } else {
          NextwebPromise<Success> _removeSafe = parent.removeSafe(nodeToBeRemoved);
          list.add(_removeSafe);
          opscb.onSuccess(list);
        }
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label, "./.label");
    Session _session = toNode.session();
    Link _LABEL = this.n.LABEL(_session);
    return _appendSafe.appendSafe(_LABEL);
  }
  
  public List<Deferred<?>> appendTypesAndIcon(final Query toNode, final FileItem source) {
    ArrayList<Deferred<?>> _xblockexpression = null;
    {
      final ArrayList<Deferred<?>> res = CollectionLiterals.<Deferred<?>>newArrayList();
      final Session session = toNode.session();
      String ext = source.getExtension();
      ext = ("." + ext);
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Link _HTML_VALUE = this.n.HTML_VALUE(session);
        Query _appendSafe = toNode.appendSafe(_HTML_VALUE);
        res.add(_appendSafe);
        Link _TEMPLATE = this.n.TEMPLATE(session);
        Query _appendSafe_1 = toNode.appendSafe(_TEMPLATE);
        res.add(_appendSafe_1);
        final Query icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon");
        res.add(icon);
        Link _ICON = this.n.ICON(session);
        Query _appendSafe_2 = icon.appendSafe(_ICON);
        res.add(_appendSafe_2);
      } else {
        boolean _equals_1 = Objects.equal(ext, ".js");
        if (_equals_1) {
          Link _JAVASCRIPT = this.n.JAVASCRIPT(session);
          Query _appendSafe_3 = toNode.appendSafe(_JAVASCRIPT);
          res.add(_appendSafe_3);
          Link _TEMPLATE_1 = this.n.TEMPLATE(session);
          Query _appendSafe_4 = toNode.appendSafe(_TEMPLATE_1);
          res.add(_appendSafe_4);
          final Query icon_1 = toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon");
          res.add(icon_1);
          Link _ICON_1 = this.n.ICON(session);
          Query _appendSafe_5 = icon_1.appendSafe(_ICON_1);
          res.add(_appendSafe_5);
        } else {
          boolean _equals_2 = Objects.equal(ext, ".coffee");
          if (_equals_2) {
            Link _COFFEESCRIPT = this.n.COFFEESCRIPT(session);
            toNode.appendSafe(_COFFEESCRIPT);
            Link _TEMPLATE_2 = this.n.TEMPLATE(session);
            toNode.appendSafe(_TEMPLATE_2);
            Query _appendSafe_6 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon");
            Link _ICON_2 = this.n.ICON(session);
            _appendSafe_6.appendSafe(_ICON_2);
          } else {
            boolean _equals_3 = Objects.equal(ext, ".css");
            if (_equals_3) {
              Link _CSS = this.n.CSS(session);
              toNode.appendSafe(_CSS);
              Link _TEMPLATE_3 = this.n.TEMPLATE(session);
              toNode.appendSafe(_TEMPLATE_3);
              Query _appendSafe_7 = toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon");
              Link _ICON_3 = this.n.ICON(session);
              _appendSafe_7.appendSafe(_ICON_3);
            } else {
              boolean _equals_4 = Objects.equal(ext, ".type");
              if (_equals_4) {
                Link _TYPE = this.n.TYPE(session);
                toNode.appendSafe(_TYPE);
                Query _appendSafe_8 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon");
                Link _ICON_4 = this.n.ICON(session);
                _appendSafe_8.appendSafe(_ICON_4);
                Query _appendSafe_9 = toNode.appendSafe("");
                Link _link = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9");
                _appendSafe_9.appendSafe(_link);
              }
            }
          }
        }
      }
      String _TEXT_VALUE = N.TEXT_VALUE();
      Link _link_1 = session.link(_TEXT_VALUE);
      Query _appendSafe_10 = toNode.appendSafe(_link_1);
      res.add(_appendSafe_10);
      _xblockexpression = res;
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
  
  @Extension
  private NextwebDataExtension ext = new NextwebDataExtension();
}
