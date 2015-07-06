package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.oehme.xtend.junit.JUnit;

/* @JUnit */@SuppressWarnings("all")
public class TestCreateFolder extends CheckNodesToFilesTemplate {
  @Override
  protected void step1_defineData() {
    this.source.append("A Folder");
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
}
