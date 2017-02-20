package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.api.Client;
import com.appjangle.api.Link;
import com.appjangle.api.LinkList;
import com.appjangle.api.LinkListQuery;
import com.appjangle.api.Node;
import com.appjangle.api.Query;
import com.appjangle.api.operations.OperationsExtension;
import com.appjangle.api.queries.QueriesExtension;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.NetworkOperationContext;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
import com.google.common.base.Objects;
import de.mxro.file.FileItem;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.async.helper.Aggregator;
import delight.functional.Closure;
import delight.functional.Success;
import io.nextweb.promise.DataOperation;
import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import io.nextweb.promise.utils.CallbackUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(N.LABEL(), N.LABEL2(), N.LABEL3()));
  
  private final Map<String, String> textValueExtensions = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of(N.HTML_VALUE(), ".html"), Pair.<String, String>of(N.TYPE(), ".type"), Pair.<String, String>of(N.CSS(), ".css"), Pair.<String, String>of(N.JAVASCRIPT(), ".js"), Pair.<String, String>of(N.COFFEESCRIPT(), ".coffee"), Pair.<String, String>of(N.RICHTEXT(), ".htm")));
  
  public boolean isTextValue(final String fileName) {
    boolean _xblockexpression = false;
    {
      final String ext = this.futils.getExtension(fileName);
      _xblockexpression = this.textValueExtensions.containsValue(("." + ext));
    }
    return _xblockexpression;
  }
  
  public boolean isTextType(final Link link) {
    return this.textValueExtensions.keySet().contains(link.uri());
  }
  
  public void getFileExtension(final Node forNode, final ValueCallback<String> cb) {
    final LinkListQuery qry = forNode.selectAllLinks();
    final ExceptionListener _function = new ExceptionListener() {
      @Override
      public void onFailure(final ExceptionResult er) {
        cb.onFailure(er.exception());
      }
    };
    qry.catchExceptions(_function);
    final Closure<LinkList> _function_1 = new Closure<LinkList>() {
      @Override
      public void apply(final LinkList links) {
        Set<Map.Entry<String, String>> _entrySet = ConvertUtils.this.textValueExtensions.entrySet();
        for (final Map.Entry<String, String> mapping : _entrySet) {
          boolean _contains = links.contains(mapping.getKey());
          if (_contains) {
            cb.onSuccess(mapping.getValue());
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
      @Override
      public void apply(final NetworkOperationContext ctx, final ValueCallback<List<DataOperation<?>>> opscb) {
        metadata.remove(cachedFile.name());
        final Link nodeToBeRemoved = ctx.session().link(address);
        final Node parent = ctx.parent();
        final ArrayList<DataOperation<?>> list = new ArrayList<DataOperation<?>>();
        boolean _hasDirectChild = ConvertUtils.this.qxt.hasDirectChild(parent.client().link(parent), nodeToBeRemoved);
        if (_hasDirectChild) {
          final Closure<Success> _function = new Closure<Success>() {
            @Override
            public void apply(final Success it) {
              opscb.onSuccess(list);
            }
          };
          final DataCallback<Success> innercb = CallbackUtils.<Success>asDataCallback(nodeToBeRemoved.getExceptionManager(), AsyncCommon.<Success>embed(opscb, _function));
          ConvertUtils.this.ext.removeRecursive(parent, nodeToBeRemoved, innercb);
        } else {
          list.add(parent.remove(nodeToBeRemoved));
          opscb.onSuccess(list);
        }
      }
    };
    ops.add(_function);
    cb.onSuccess(ops);
  }
  
  public Query appendLabel(final Query toNode, final String label) {
    return toNode.appendSafe(label, "./.label").appendSafe(this.n.LABEL(toNode.client()));
  }
  
  public List<DataOperation<?>> appendTypesAndIcon(final Query toNode, final FileItem source) {
    ArrayList<DataOperation<?>> _xblockexpression = null;
    {
      final ArrayList<DataOperation<?>> res = CollectionLiterals.<DataOperation<?>>newArrayList();
      final Client session = toNode.client();
      String ext = source.getExtension();
      ext = ("." + ext);
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        res.add(toNode.appendSafe(this.n.HTML_VALUE(session)));
        res.add(toNode.appendSafe(this.n.TEMPLATE(session)));
        final Query icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon");
        res.add(icon);
        res.add(icon.appendSafe(this.n.ICON(session)));
      } else {
        boolean _equals_1 = Objects.equal(ext, ".htm");
        if (_equals_1) {
          res.add(toNode.appendSafe(this.n.RICHTEXT(session)));
          res.add(toNode.appendSafe(this.n.TEMPLATE(session)));
          final Query icon_1 = toNode.appendSafe("https://appjangle.com/files/img/20141119/RTF.png", "./.icon");
          res.add(icon_1);
          res.add(icon_1.appendSafe(this.n.ICON(session)));
        } else {
          boolean _equals_2 = Objects.equal(ext, ".js");
          if (_equals_2) {
            res.add(toNode.appendSafe(this.n.JAVASCRIPT(session)));
            res.add(toNode.appendSafe(this.n.TEMPLATE(session)));
            final Query icon_2 = toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon");
            res.add(icon_2);
            res.add(icon_2.appendSafe(this.n.ICON(session)));
          } else {
            boolean _equals_3 = Objects.equal(ext, ".coffee");
            if (_equals_3) {
              res.add(toNode.appendSafe(this.n.COFFEESCRIPT(session)));
              res.add(toNode.appendSafe(this.n.TEMPLATE(session)));
              final Query icon_3 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon");
              res.add(icon_3);
              res.add(icon_3.appendSafe(this.n.ICON(session)));
            } else {
              boolean _equals_4 = Objects.equal(ext, ".css");
              if (_equals_4) {
                res.add(toNode.appendSafe(this.n.CSS(session)));
                res.add(toNode.appendSafe(this.n.TEMPLATE(session)));
                final Query icon_4 = toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon");
                res.add(icon_4);
                res.add(icon_4.appendSafe(this.n.ICON(session)));
              } else {
                boolean _equals_5 = Objects.equal(ext, ".type");
                if (_equals_5) {
                  res.add(toNode.appendSafe(this.n.TYPE(session)));
                  final Query icon_5 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon");
                  res.add(icon_5);
                  res.add(icon_5.appendSafe(this.n.ICON(session)));
                  final Query description = toNode.appendSafe("");
                  res.add(description);
                  res.add(description.appendSafe(session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9")));
                }
              }
            }
          }
        }
      }
      res.add(toNode.appendSafe(this.n.TEXT_VALUE(session)));
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public final static Object NO_VALUE = new Object();
  
  public void getFileName(final Node forNode, final FileItem inFolder, final String fileExtension, final ValueCallback<String> cb) {
    final Closure<String> _function = new Closure<String>() {
      @Override
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
    this.getFileName(forNode, 
      AsyncCommon.<String>embed(cb, _function));
  }
  
  public void getFileName(final Node fromNode, final ValueCallback<String> cb) {
    final Closure<List<Object>> _function = new Closure<List<Object>>() {
      @Override
      public void apply(final List<Object> res) {
        for (final Object item : res) {
          if ((item instanceof String)) {
            cb.onSuccess(((String)item));
            return;
          }
        }
        cb.onSuccess(ConvertUtils.getNameFromUri(fromNode.uri()));
      }
    };
    final Aggregator<Object> cbs = AsyncCommon.<Object>collect(this.labelTypes.size(), 
      AsyncCommon.<List<Object>>embed(cb, _function));
    final Consumer<String> _function_1 = new Consumer<String>() {
      @Override
      public void accept(final String labelType) {
        final Query qry = fromNode.select(fromNode.client().link(labelType));
        final ValueCallback<Object> itmcb = cbs.createCallback();
        final UndefinedListener _function = new UndefinedListener() {
          @Override
          public void onUndefined(final UndefinedResult it) {
            itmcb.onSuccess(ConvertUtils.NO_VALUE);
          }
        };
        qry.catchUndefined(_function);
        final ExceptionListener _function_1 = new ExceptionListener() {
          @Override
          public void onFailure(final ExceptionResult er) {
            itmcb.onFailure(er.exception());
          }
        };
        qry.catchExceptions(_function_1);
        final Closure<Node> _function_2 = new Closure<Node>() {
          @Override
          public void apply(final Node label) {
            itmcb.onSuccess(label.value());
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
  private OperationsExtension ext = new OperationsExtension();
  
  @Extension
  private QueriesExtension qxt = new QueriesExtension();
  
  @Extension
  private FileUtils futils = new FileUtils();
}
