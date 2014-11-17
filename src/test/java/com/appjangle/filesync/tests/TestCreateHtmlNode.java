package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckFilesToNodesTemplate;
import de.mxro.file.FileItem;

@SuppressWarnings("all")
public class TestCreateHtmlNode extends CheckFilesToNodesTemplate {
  protected void step1_defineFiles() {
    FileItem _createFile = this.source.createFile("My Document.html");
    _createFile.setText("<html></html>");
  }
  
  protected void step2_assertNodes() {
  }
}
