package com.appjangle.filesync.tests;

import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.oehme.xtend.junit.JUnit;
import io.nextweb.Link;
import io.nextweb.Query;
import io.nextweb.Session;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit */@SuppressWarnings("all")
public class TestRecursiveSync extends CheckNodesToFilesTemplate {
  @Override
  protected boolean doRecursiveSync() {
    return true;
  }
  
  @Override
  protected void step1_defineData() {
    Query _append = this.source.append("oh my", "./node1");
    _append.append("And in the subfolder", "./sub");
    Query _append_1 = this.source.append("Hello");
    _append_1.append("Any another foder");
    final Query node3 = this.source.append("node3", "./node3");
    Query _append_2 = node3.append("child1", "./child1");
    Query _append_3 = _append_2.append("b", "./inThere");
    _append_3.get();
    Query _append_4 = node3.append("child2");
    _append_4.append("c");
    final Query html = node3.append("<html></html>", "./html");
    Query _append_5 = html.append("My Html Document", "./.label");
    Session _session = this.source.session();
    Link _LABEL = this.n.LABEL(_session);
    _append_5.append(_LABEL);
    Session _session_1 = this.source.session();
    Link _HTML_VALUE = this.n.HTML_VALUE(_session_1);
    html.append(_HTML_VALUE);
    Query _append_6 = this.source.append("node4", "./node4");
    Session _session_2 = this.source.session();
    Link _link = _session_2.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/3/n1");
    _append_6.append(_link);
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from String to Procedure1<? super String>"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>");
  }
  
  @Extension
  private N n = new N();
}
