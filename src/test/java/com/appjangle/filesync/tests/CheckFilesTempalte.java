package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.jre.AppjangleJre;
import de.mxro.async.Deferred;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class CheckFilesTempalte {
  protected LocalServer server;
  
  protected Session session;
  
  protected Node source;
  
  protected File target;
  
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
    NextwebPromise<Success> _commit = this.session.commit();
    _commit.get();
    final Deferred<Success> _function = new Deferred<Success>() {
      public void get(final ValueCallback<Success> cb) {
        FileSync.sync(CheckFilesTempalte.this.target, CheckFilesTempalte.this.source, cb);
      }
    };
    AsyncJre.<Success>waitFor(_function);
    this.assertFiles();
  }
  
  @After
  public void tearDown() {
    NextwebPromise<Success> _close = this.session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = this.server.shutdown();
    _shutdown.get();
  }
}
