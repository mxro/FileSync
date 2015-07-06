package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckUpdatesTemplate;
import de.oehme.xtend.junit.JUnit;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;

/* @JUnit */@SuppressWarnings("all")
public class TestRemoveFolder extends CheckUpdatesTemplate {
  @Override
  protected void step1_defineData() {
    this.source.append("folder1", "./folder1");
    this.source.append("folder2", "./folder2");
    this.source.append("folder3", "./folder3");
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
  
  @Override
  protected void step3_updateNodes() {
    Query _select = this.source.select("./folder1");
    this.source.remove(_select);
  }
  
  @Override
  protected void step4_assertFilesAfterUpdate() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
  
  @Override
  protected void step5_updateFiles() {
    this.result.deleteFolder("folder2");
  }
  
  @Override
  protected void step6_assertNodesAfterUpdate() {
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> cb) {
        final Query qry = TestRemoveFolder.this.source.select("./folder2");
        final ExceptionListener _function = new ExceptionListener() {
          @Override
          public void onFailure(final ExceptionResult er) {
            Throwable _exception = er.exception();
            cb.onFailure(_exception);
          }
        };
        qry.catchExceptions(_function);
        final UndefinedListener _function_1 = new UndefinedListener() {
          @Override
          public void onUndefined(final UndefinedResult it) {
            cb.onSuccess(Success.INSTANCE);
          }
        };
        qry.catchUndefined(_function_1);
        final Closure<Node> _function_2 = new Closure<Node>() {
          @Override
          public void apply(final Node it) {
            Exception _exception = new Exception("Node should have been removed.");
            cb.onFailure(_exception);
          }
        };
        qry.get(_function_2);
      }
    };
    Async.<Success>waitFor(_function);
  }
}
