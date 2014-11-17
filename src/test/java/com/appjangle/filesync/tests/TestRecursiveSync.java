package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import io.nextweb.Query;

@SuppressWarnings("all")
public class TestRecursiveSync extends CheckNodesToFilesTemplate {
  protected boolean doRecursiveSync() {
    return true;
  }
  
  protected void step1_defineData() {
    Query _append = this.source.append("oh my");
    _append.append("And in the subfolder");
    Query _append_1 = this.source.append("Hello");
    _append_1.append("Any another foder");
    final Query node3 = this.source.append("node3");
    Query _append_2 = node3.append("child1");
    _append_2.append("b");
    Query _append_3 = node3.append("child2");
    _append_3.append("c");
  }
  
  protected void step2_assertFiles() {
  }
}
