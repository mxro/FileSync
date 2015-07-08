package com.appjangle.filesync.tests;

import com.appjangle.api.Link;
import com.appjangle.api.Node;
import com.appjangle.api.Query;
import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckUpdatesTemplate;
import de.oehme.xtend.junit.JUnit;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit */@SuppressWarnings("all")
public class TestRemoveHtmlFile extends CheckUpdatesTemplate {
  @Override
  protected void step1_defineData() {
    final Query file1 = this.source.append("<p>file1</p>", "./file1");
    Query _append = file1.append("html1", "./label");
    Link _LABEL = this.n.LABEL(this.session);
    _append.append(_LABEL);
    Link _HTML_VALUE = this.n.HTML_VALUE(this.session);
    file1.append(_HTML_VALUE);
    final Query file2 = this.source.append("file2", "./file2");
    Query _append_1 = file2.append("html2");
    Link _LABEL_1 = this.n.LABEL(this.session);
    _append_1.append(_LABEL_1);
    Link _HTML_VALUE_1 = this.n.HTML_VALUE(this.session);
    file2.append(_HTML_VALUE_1);
    final Query file3 = this.source.append("file3", "./file3");
    Query _append_2 = file3.append("html3");
    Link _LABEL_2 = this.n.LABEL(this.session);
    _append_2.append(_LABEL_2);
    Link _HTML_VALUE_2 = this.n.HTML_VALUE(this.session);
    file3.append(_HTML_VALUE_2);
  }
  
  @Override
  protected void step2_assertFiles() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Procedure1<? super Integer>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
  
  @Override
  protected void step3_updateNodes() {
    final Query html = this.source.select("./file1");
    Query _select = html.select("./label");
    Link _LABEL = this.n.LABEL(this.session);
    _select.remove(_LABEL);
    Query _select_1 = html.select("./label");
    html.remove(_select_1);
    Link _HTML_VALUE = this.n.HTML_VALUE(this.session);
    html.remove(_HTML_VALUE);
    this.source.remove(html);
  }
  
  @Override
  protected void step4_assertFilesAfterUpdate() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
  
  @Override
  protected void step5_updateFiles() {
    this.result.deleteFile("html2.html");
  }
  
  @Override
  protected void step6_assertNodesAfterUpdate() {
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> cb) {
        final Query qry = TestRemoveHtmlFile.this.source.select("./file2");
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
  
  @Extension
  private N n = new N();
}
