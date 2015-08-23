package com.appjangle.filesync.tests;

import com.appjangle.api.Client;
import com.appjangle.api.Node;
import com.appjangle.api.common.LocalServer;
import com.appjangle.filesync.FileSync;
import de.mxro.file.FileItem;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Success;
import io.nextweb.promise.NextwebPromise;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public abstract class CheckNodesToFilesTemplate {
  protected LocalServer server;
  
  protected Client session;
  
  protected Node source;
  
  protected File target;
  
  protected FileItem result;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Servers is undefined for the type CheckNodesToFilesTemplate"
      + "\ncreateAndStart cannot be resolved");
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
      @Override
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
    Async.<Success>waitFor(_function);
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
