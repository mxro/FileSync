package com.appjangle.filesync.tests;

import com.appjangle.api.Query;
import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestRecursiveSync extends CheckNodesToFilesTemplate {
  @Override
  protected boolean doRecursiveSync() {
    return true;
  }
  
  @Override
  protected void step1_defineData() {
    this.source.append("oh my", "./node1").append("And in the subfolder", "./sub");
    this.source.append("Hello").append("Any another foder");
    final Query node3 = this.source.append("node3", "./node3");
    node3.append("child1", "./child1").append("b", "./inThere").get();
    node3.append("child2").append("c");
    final Query html = node3.append("<html></html>", "./html");
    html.append("My Html Document", "./.label").append(this.n.LABEL(this.source.client()));
    html.append(this.n.HTML_VALUE(this.source.client()));
    this.source.append("node4", "./node4").append(this.source.client().link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/3/n1"));
  }
  
  @Override
  protected void step2_assertFiles() {
    boolean _exists = this.result.get("node1").exists();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists), Boolean.valueOf(true));
    boolean _exists_1 = this.result.get("node1").get("sub").exists();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists_1), Boolean.valueOf(true));
    boolean _exists_2 = this.result.get("node3").exists();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists_2), Boolean.valueOf(true));
    boolean _exists_3 = this.result.get("node3").get("child1").exists();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists_3), Boolean.valueOf(true));
    boolean _exists_4 = this.result.get("node3").get("child1").get("inThere").exists();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists_4), Boolean.valueOf(true));
    boolean _isDirectory = this.result.get("node3").get("child1").get("inThere").isDirectory();
    TestRecursiveSync.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_isDirectory), Boolean.valueOf(true));
    String _text = this.result.get("node3").get("My Html Document.html").getText();
    TestRecursiveSync.<String, String>operator_doubleArrow(_text, "<html></html>");
    int _size = this.result.get("node4").get("Plain Text Editor").getChildren().size();
    TestRecursiveSync.<Integer, Integer>operator_doubleArrow(Integer.valueOf(_size), Integer.valueOf(0));
  }
  
  @Extension
  private N n = new N();
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertFalse(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertTrue(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void fail() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static void fail(final String message) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestRecursiveSync is already defined in TestRecursiveSync.java.");
  }
}
