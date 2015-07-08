package com.appjangle.filesync.tests;

import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckFilesToNodesTemplate;
import de.mxro.file.FileItem;
import de.oehme.xtend.junit.Hamcrest;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit
@Hamcrest */@SuppressWarnings("all")
public class TestCreateHtmlNode extends CheckFilesToNodesTemplate {
  @Override
  protected void step1_defineFiles() {
    FileItem _createFile = this.source.createFile("My Document.html");
    _createFile.setText("<html></html>");
  }
  
  @Override
  protected void step2_assertNodes() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method equalTo is undefined for the type TestCreateHtmlNode"
      + "\nThe method equalTo is undefined for the type TestCreateHtmlNode"
      + "\nThe method equalTo is undefined for the type TestCreateHtmlNode");
  }
  
  @Extension
  private N n = new N();
}
