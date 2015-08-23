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
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Servers is undefined for the type CheckFilesToNodesTemplate"
      + "\ncreateAndStart cannot be resolved");
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
