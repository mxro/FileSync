package com.appjangle.filesync.tests;

import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckFilesToNodesTemplate;
import de.oehme.xtend.junit.Hamcrest;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit
@Hamcrest */@SuppressWarnings("all")
public class TestCreateNode extends CheckFilesToNodesTemplate {
  @Override
  protected void step1_defineFiles() {
    this.source.assertFolder("Oh my test");
  }
  
  @Override
  protected void step2_assertNodes() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field notNullValue is undefined for the type TestCreateNode"
      + "\nThe method equalTo is undefined for the type TestCreateNode"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>");
  }
  
  @Extension
  private N n = new N();
}
