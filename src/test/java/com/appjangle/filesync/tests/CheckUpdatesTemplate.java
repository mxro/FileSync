package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.mxro.async.Deferred;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.fn.Success;
import io.nextweb.promise.NextwebPromise;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;

@SuppressWarnings("all")
public abstract class CheckUpdatesTemplate extends CheckNodesToFilesTemplate {
  protected abstract void step3_updateNodes();
  
  protected abstract void step4_assertFilesAfterUpdate();
  
  protected abstract void step5_updateFiles();
  
  protected abstract void step6_assertNodesAfterUpdate();
  
  @Test
  public void test() {
    try {
      this.step1_defineData();
      NextwebPromise<Success> _commit = this.session.commit();
      _commit.get();
      final Deferred<Success> _function = new Deferred<Success>() {
        public void get(final ValueCallback<Success> cb) {
          FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      AsyncJre.<Success>waitFor(_function);
      this.step2_assertFiles();
      this.step3_updateNodes();
      NextwebPromise<Success> _commit_1 = this.session.commit();
      _commit_1.get();
      final Deferred<Success> _function_1 = new Deferred<Success>() {
        public void get(final ValueCallback<Success> cb) {
          FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      AsyncJre.<Success>waitFor(_function_1);
      this.step4_assertFilesAfterUpdate();
      Thread.sleep(400);
      this.step5_updateFiles();
      final Deferred<Success> _function_2 = new Deferred<Success>() {
        public void get(final ValueCallback<Success> cb) {
          FileSync.sync(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      AsyncJre.<Success>waitFor(_function_2);
      this.step6_assertNodesAfterUpdate();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
