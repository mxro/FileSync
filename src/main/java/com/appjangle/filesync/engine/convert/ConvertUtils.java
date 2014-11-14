package com.appjangle.filesync.engine.convert;

import com.google.common.collect.Lists;
import de.mxro.async.callbacks.ValueCallback;
import io.nextweb.Node;
import io.nextweb.Query;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(Lists.<String>newArrayList("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"));
  
  public Query appendLabel(final Query toNode, final String label) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The field labelTypes is not applicable for the arguments ((Object)=>int)"
      + "\nType mismatch: cannot convert from List<String> to Link");
  }
  
  public Object getFileName(final Node fromNode, final ValueCallback<String> cb) {
    return null;
  }
}
