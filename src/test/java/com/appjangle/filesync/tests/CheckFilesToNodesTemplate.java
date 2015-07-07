package com.appjangle.filesync.tests;

import com.appjangle.api.Client;
import com.appjangle.api.Node;
import com.appjangle.api.Query;
import com.appjangle.api.common.LocalServer;
import com.appjangle.filesync.FileSync;
import com.appjangle.jre.AppjangleJre;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Success;
import io.nextweb.promise.NextwebPromise;
import java.io.File;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class CheckFilesToNodesTemplate {
  protected LocalServer server;
  
  protected Client session;
  
  protected Node result;
  
  protected File sourceFolder;
  
  protected FileItem source;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    try {
      LocalServer _startServer = AppjangleJre.startServer();
      this.server = _startServer;
      Client _createSession = AppjangleJre.createSession(this.server);
      this.session = _createSession;
      Query _seed = this.session.seed(this.server);
      Node _get = _seed.get();
      this.result = _get;
      File _newFolder = this.tempFolder.newFolder("sync1");
      this.sourceFolder = _newFolder;
      FileItem _wrap = FilesJre.wrap(this.sourceFolder);
      this.source = _wrap;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected abstract void step1_defineFiles();
  
  protected abstract void step2_assertNodes();
  
  @Test
  public void test() {
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> cb) {
        FileSync.syncSingleFolder(CheckFilesToNodesTemplate.this.sourceFolder, CheckFilesToNodesTemplate.this.result, cb);
      }
    };
    Async.<Success>waitFor(_function);
    this.step1_defineFiles();
    final Operation<Success> _function_1 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> cb) {
        FileSync.syncSingleFolder(CheckFilesToNodesTemplate.this.sourceFolder, CheckFilesToNodesTemplate.this.result, cb);
      }
    };
    Async.<Success>waitFor(_function_1);
    NextwebPromise<Success> _commit = this.session.commit();
    _commit.get();
    this.step2_assertNodes();
  }
  
  @After
  public void tearDown() {
    NextwebPromise<Success> _close = this.session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = this.server.shutdown();
    _shutdown.get();
  }
}
