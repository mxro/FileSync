package com.appjangle.filesync.tests;

import com.appjangle.api.Link;
import com.appjangle.api.Query;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.oehme.xtend.junit.JUnit;

/* @JUnit */@SuppressWarnings("all")
public class TestCreateMultipleFiles extends CheckNodesToFilesTemplate {
  @Override
  protected void step1_defineData() {
    final Query html = this.source.append("<html></html>", "./html");
    Query _append = html.append("Html Document");
    Link _link = this.session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel");
    _append.append(_link);
    Link _link_1 = this.session.link("https://admin1.linnk.it/types/v01/isHtmlValue");
    html.append(_link_1);
    this.source.append("Folder 1");
    this.source.append("Folder 2");
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from String to Procedure1<? super String>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
}
