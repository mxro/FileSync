package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.jre.AppjangleJre;
import de.mxro.async.Operation;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import java.io.File;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
    this.step1_defineData();
    NextwebPromise<Success> _commit = this.session.commit();
    _commit.get();
    final Operation<Success> _function = new Operation<Success>() {
      public void apply(final ValueCallback<Success> cb) {
        boolean _doRecursiveSync = CheckNodesToFilesTemplate.this.doRecursiveSync();
        boolean _not = (!_doRecursiveSync);
        if (_not) {
          FileSync.syncSingleFolder(CheckNodesToFilesTemplate.this.result, CheckNodesToFilesTemplate.this.source, cb);
        } else {
          FileSync.sync(CheckNodesToFilesTemplate.this.result, CheckNodesToFilesTemplate.this.source, cb);
        }
      }
    };
    AsyncJre.<Success>waitFor(_function);
    this.step2_assertFiles();
  }
  
  @After
  public void tearDown() {
    NextwebPromise<Success> _close = this.session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = this.server.shutdown();
    _shutdown.get();
  }
}
