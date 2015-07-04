package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.mxro.fn.Success;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
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
  @Override
  public void test() {
    try {
      this.step1_defineData();
      NextwebPromise<Success> _commit = this.session.commit();
      _commit.get();
      final Operation<Success> _function = new Operation<Success>() {
        @Override
        public void apply(final ValueCallback<Success> cb) {
          FileSync.syncSingleFolder(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      Async.<Success>waitFor(_function);
      this.step2_assertFiles();
      this.step3_updateNodes();
      NextwebPromise<Success> _commit_1 = this.session.commit();
      _commit_1.get();
      final Operation<Success> _function_1 = new Operation<Success>() {
        @Override
        public void apply(final ValueCallback<Success> cb) {
          FileSync.syncSingleFolder(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      Async.<Success>waitFor(_function_1);
      this.step4_assertFilesAfterUpdate();
      Thread.sleep(2000);
      this.step5_updateFiles();
      final Operation<Success> _function_2 = new Operation<Success>() {
        @Override
        public void apply(final ValueCallback<Success> cb) {
          FileSync.syncSingleFolder(CheckUpdatesTemplate.this.target, CheckUpdatesTemplate.this.source, cb);
        }
      };
      Async.<Success>waitFor(_function_2);
      this.step6_assertNodesAfterUpdate();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
