package com.appjangle.filesync.tests;

import de.mxro.file.FileItem;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class CheckFilesToNodesTemplate {
  protected /* LocalServer */Object server;
  
  protected /* Session */Object session;
  
  protected /* Node */Object result;
  
  protected File sourceFolder;
  
  protected FileItem source;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method startServer is undefined for the type CheckFilesToNodesTemplate"
      + "\nThe method createSession is undefined for the type CheckFilesToNodesTemplate"
      + "\nseed cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  protected abstract void step1_defineFiles();
  
  protected abstract void step2_assertNodes();
  
  @Test
  public void test() {
    throw new Error("Unresolved compilation problems:"
      + "\ncommit cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  @After
  public void tearDown() {
    throw new Error("Unresolved compilation problems:"
      + "\nclose cannot be resolved"
      + "\nget cannot be resolved"
      + "\nshutdown cannot be resolved"
      + "\nget cannot be resolved");
  }
}
