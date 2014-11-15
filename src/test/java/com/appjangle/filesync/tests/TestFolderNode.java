package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;

@SuppressWarnings("all")
public class TestFolderNode extends CheckNodesToFilesTemplate {
  protected void defineData() {
    this.source.append("A Folder");
  }
  
  protected void assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
}
