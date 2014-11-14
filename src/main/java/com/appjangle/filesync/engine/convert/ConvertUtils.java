package com.appjangle.filesync.engine.convert;

import io.nextweb.Link;
import io.nextweb.Query;
import io.nextweb.Session;

@SuppressWarnings("all")
public class ConvertUtils {
  public static Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label);
    Session _session = toNode.session();
    Link _link = _session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel");
    return _appendSafe.appendSafe(_link, "./label");
  }
}
