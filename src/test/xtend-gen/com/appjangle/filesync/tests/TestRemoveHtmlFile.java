package com.appjangle.filesync.tests;

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
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestRemoveHtmlFile extends CheckUpdatesTemplate {
  @Override
  protected void step1_defineData() {
    final Query file1 = this.source.append("<p>file1</p>", "./file1");
    file1.append("html1", "./label").append(this.n.LABEL(this.session));
    file1.append(this.n.HTML_VALUE(this.session));
    final Query file2 = this.source.append("file2", "./file2");
    file2.append("html2").append(this.n.LABEL(this.session));
    file2.append(this.n.HTML_VALUE(this.session));
    final Query file3 = this.source.append("file3", "./file3");
    file3.append("html3").append(this.n.LABEL(this.session));
    file3.append(this.n.HTML_VALUE(this.session));
  }
  
  @Override
  protected void step2_assertFiles() {
    int _size = this.result.getChildren().size();
    TestRemoveHtmlFile.<Integer, Integer>operator_doubleArrow(Integer.valueOf(_size), Integer.valueOf(4));
    boolean _exists = this.result.get("html1.html").exists();
    TestRemoveHtmlFile.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists), Boolean.valueOf(true));
  }
  
  @Override
  protected void step3_updateNodes() {
    final Query html = this.source.select("./file1");
    html.select("./label").remove(this.n.LABEL(this.session));
    html.remove(html.select("./label"));
    html.remove(this.n.HTML_VALUE(this.session));
    this.source.remove(html);
  }
  
  @Override
  protected void step4_assertFilesAfterUpdate() {
    boolean _exists = this.result.get("html1.html").exists();
    TestRemoveHtmlFile.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists), Boolean.valueOf(false));
    this.source.select("./file2").get();
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
            cb.onFailure(er.exception());
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
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertFalse(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertTrue(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void fail() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static void fail(final String message) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRemoveHtmlFile is already defined in TestRemoveHtmlFile.java.");
  }
}
