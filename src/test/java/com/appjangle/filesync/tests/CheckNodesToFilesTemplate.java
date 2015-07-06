package com.appjangle.filesync.tests;

import de.mxro.file.FileItem;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class CheckNodesToFilesTemplate {
  protected /* LocalServer */Object server;
  
  protected /* Session */Object session;
  
  protected /* Node */Object source;
  
  protected File target;
  
  protected FileItem result;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method startServer is undefined for the type CheckNodesToFilesTemplate"
      + "\nThe method createSession is undefined for the type CheckNodesToFilesTemplate"
      + "\nseed cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  protected boolean doRecursiveSync() {
    return false;
  }
  
  protected abstract void step1_defineData();
  
  protected abstract void step2_assertFiles();
  
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
