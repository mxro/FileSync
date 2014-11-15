package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.mxro.async.Deferred;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.fn.Success;
import io.nextweb.promise.NextwebPromise;

@SuppressWarnings("all")
public abstract class CheckUpdatesTemplate extends CheckNodesToFilesTemplate {
  protected abstract void updateNodes();
  
  protected abstract void assertFilesAfterUpdate();
  
  protected abstract void updateFiles();
  
  protected abstract void assertNodesAfterUpdate();
  
  public void test() {
    this.defineData();
    NextwebPromise<Success> _commit = this.session.commit();
    _commit.get();
    final Deferred<Success> _function = new Deferred<Success>() {
      public void get(final ValueCallback<Success> cb) {
        FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
      }
    };
    AsyncJre.<Success>waitFor(_function);
    this.assertFiles();
    this.updateNodes();
    final Deferred<Success> _function_1 = new Deferred<Success>() {
      public void get(final ValueCallback<Success> cb) {
        FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
      }
    };
    AsyncJre.<Success>waitFor(_function_1);
    this.assertFilesAfterUpdate();
    this.updateFiles();
    final Deferred<Success> _function_2 = new Deferred<Success>() {
      public void get(final ValueCallback<Success> cb) {
        FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
      }
    };
    AsyncJre.<Success>waitFor(_function_2);
    this.assertNodesAfterUpdate();
  }
}
