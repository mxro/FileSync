package com.appjangle.filesync.engine.convert;

import com.google.common.collect.Lists;
import de.mxro.async.Aggregator;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(Lists.<String>newArrayList("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"));
  
  public Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label);
    Session _session = toNode.session();
    String _get = this.labelTypes.get(0);
    Link _link = _session.link(_get);
    return _appendSafe.appendSafe(_link, "./label");
  }
  
  public Aggregator<Object> getFileName(final Node fromNode, final ValueCallback<String> cb) {
    int _size = this.labelTypes.size();
    final Closure<List<Object>> _function = new Closure<List<Object>>() {
      public void apply(final List<Object> res) {
      }
    };
    ValueCallback<List<Object>> _embed = Async.<List<Object>>embed(cb, _function);
    return Async.<Object>collect(_size, _embed);
  }
}