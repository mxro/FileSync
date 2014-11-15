package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import io.nextweb.Link;
import io.nextweb.Query;

@SuppressWarnings("all")
public class TestNodeWithLabel extends CheckNodesToFilesTemplate {
  protected void defineData() {
    Query _append = this.source.append("No value", "./value");
    Query _append_1 = _append.append("Labelled Node");
    Link _link = this.session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel");
    _append_1.append(_link);
  }
  
  protected void assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
}
