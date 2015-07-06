package com.appjangle.filesync.tests;

import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckFilesToNodesTemplate;
import de.mxro.file.FileItem;
import de.oehme.xtend.junit.Hamcrest;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit
@Hamcrest */@SuppressWarnings("all")
public class TestCreateVariousNodeTypes extends CheckFilesToNodesTemplate {
  @Override
  protected void step1_defineFiles() {
    FileItem _createFile = this.source.createFile("My Document.css");
    _createFile.setText(".class {}");
    FileItem _createFile_1 = this.source.createFile("My Script.js");
    _createFile_1.setText("window.alert(\'nothing much\');");
    FileItem _createFile_2 = this.source.createFile("My CoffeeScript.coffee");
    _createFile_2.setText("window.alert \'nothing much\'");
    FileItem _createFile_3 = this.source.createFile("My type.type");
    _createFile_3.setText("Something for my type");
  }
  
  @Override
  protected void step2_assertNodes() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes"
      + "\nThe method equalTo is undefined for the type TestCreateVariousNodeTypes");
  }
  
  @Extension
  private N n = new N();
}
