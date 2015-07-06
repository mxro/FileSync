package com.appjangle.filesync.tests;

import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.oehme.xtend.junit.JUnit;
import io.nextweb.Link;
import io.nextweb.Query;

/* @JUnit */@SuppressWarnings("all")
public class TestCreateHtmlFile extends CheckNodesToFilesTemplate {
  @Override
  protected void step1_defineData() {
    final Query html = this.source.append("<html></html>", "./html");
    Query _append = html.append("Html Document");
    String _LABEL = N.LABEL();
    Link _link = this.session.link(_LABEL);
    _append.append(_link);
    String _HTML_VALUE = N.HTML_VALUE();
    Link _link_1 = this.session.link(_HTML_VALUE);
    html.append(_link_1);
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from String to Procedure1<? super String>");
  }
}
