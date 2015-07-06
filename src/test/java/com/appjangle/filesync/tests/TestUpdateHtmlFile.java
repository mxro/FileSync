package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckUpdatesTemplate;
import de.mxro.file.FileItem;
import de.oehme.xtend.junit.JUnit;
import io.nextweb.Link;
import io.nextweb.Query;

/* @JUnit */@SuppressWarnings("all")
public class TestUpdateHtmlFile extends CheckUpdatesTemplate {
  @Override
  protected void step1_defineData() {
    final Query html = this.source.append("<p>Hello 1</p>", "./html");
    Query _append = html.append("doc");
    Link _link = this.session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel");
    _append.append(_link);
    Link _link_1 = this.session.link("https://admin1.linnk.it/types/v01/isHtmlValue");
    html.append(_link_1);
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from String to Procedure1<? super String>");
  }
  
  @Override
  protected void step3_updateNodes() {
    Query _select = this.source.select("./html");
    _select.setValue("<p>Hello 1 and Hello 2 are an amazing team.</p>");
  }
  
  @Override
  protected void step4_assertFilesAfterUpdate() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from String to Procedure1<? super String>");
  }
  
  @Override
  protected void step5_updateFiles() {
    FileItem _get = this.result.get("doc.html");
    _get.setText("And now for something different");
  }
  
  @Override
  protected void step6_assertNodesAfterUpdate() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from String to Procedure1<? super Object>");
  }
}
