package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;

@SuppressWarnings("all")
public class TestRecursiveSync extends CheckNodesToFilesTemplate {
  protected boolean doRecursiveSync() {
    return true;
  }
  
  protected void step1_defineData() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  protected void step2_assertFiles() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
