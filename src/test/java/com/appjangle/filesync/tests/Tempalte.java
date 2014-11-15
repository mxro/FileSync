package com.appjangle.filesync.tests;

import com.appjangle.jre.AppjangleJre;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import java.io.File;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class Tempalte {
  private LocalServer server;
  
  private Session session;
  
  private Node source;
  
  private File target;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    LocalServer _startServer = AppjangleJre.startServer();
    this.server = _startServer;
    Session _createSession = AppjangleJre.createSession(this.server);
    this.session = _createSession;
    Query _seed = this.session.seed(this.server);
    Node _get = _seed.get();
    this.source = _get;
    File _newFolder = this.tempFolder.newFolder("sync1");
    this.target = _newFolder;
  }
  
  public abstract void defineData();
  
  public abstract void assertFiles();
  
  @Test
  public void test() {
    this.defineData();
  }
}
