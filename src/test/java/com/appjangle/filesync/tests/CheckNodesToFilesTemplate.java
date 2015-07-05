package com.appjangle.filesync.tests;

import java.io.File;

import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.appjangle.jre.AppjangleJre;

import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;

@SuppressWarnings("all")
public abstract class CheckNodesToFilesTemplate {
  protected LocalServer server;
  
  protected Session session;
  
  protected Node source;
  
  protected File target;
  
  protected FileItem result;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    try {
      LocalServer _startServer = AppjangleJre.startServer();
      this.server = _startServer;
      Session _createSession = AppjangleJre.createSession(this.server);
      this.session = _createSession;
      Query _seed = this.session.seed(this.server);
      Node _get = _seed.get();
      this.source = _get;
      File _newFolder = this.tempFolder.newFolder("sync1");
      this.target = _newFolder;
      FileItem _wrap = FilesJre.wrap(this.target);
      this.result = _wrap;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected boolean doRecursiveSync() {
    return false;
  }
  
  protected abstract void step1_defineData();
  
  protected abstract void step2_assertFiles();
  
  @Test
  public void test() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method commit is undefined for the type CheckNodesToFilesTemplate"
      + "\nget cannot be resolved");
  }
  
  @After
  public void tearDown() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method close is undefined for the type CheckNodesToFilesTemplate"
      + "\nThe method shutdown is undefined for the type CheckNodesToFilesTemplate"
      + "\nget cannot be resolved"
      + "\nget cannot be resolved");
  }
}
