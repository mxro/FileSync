package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckFilesTempalte;

@SuppressWarnings("all")
public class TestSimpleNode extends CheckFilesTempalte {
  public void defineData() {
    this.source.append("A Folder");
  }
  
  public void assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method assertThat(T, Matcher<T>) is not visible"
      + "\nType mismatch: cannot convert from int to Matcher<Integer>");
  }
}
