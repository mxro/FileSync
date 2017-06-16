package com.appjangle.filesync.tests;

import com.appjangle.api.Client;
import com.appjangle.api.Node;
import com.appjangle.api.common.LocalServer;
import com.appjangle.api.servers.jre.Servers;
import com.appjangle.filesync.FileSync;
import com.appjangle.jre.Clients;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Success;
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
  
  protected Client session;
  
  protected Node source;
  
  protected File target;
  
  protected FileItem result;
  
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Before
  public void setUp() {
    try {
      this.server = Servers.createAndStart();
      this.session = Clients.create(this.server);
      this.source = this.session.seed(this.server).get();
      this.target = this.tempFolder.newFolder("sync1");
      this.result = FilesJre.wrap(this.target);
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
    this.session.commit().get();
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
    this.session.close().get();
    this.server.shutdown().get();
  }
}
