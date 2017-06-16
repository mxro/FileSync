package com.appjangle.filesync.tests;

import com.appjangle.api.Query;
import com.appjangle.filesync.tests.CheckUpdatesTemplate;
import de.mxro.file.FileItem;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestUpdateHtmlFile extends CheckUpdatesTemplate {
  @Override
  protected void step1_defineData() {
    final Query html = this.source.append("<p>Hello 1</p>", "./html");
    html.append("doc").append(this.session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel"));
    html.append(this.session.link("https://admin1.linnk.it/types/v01/isHtmlValue"));
  }
  
  @Override
  protected void step2_assertFiles() {
    String _text = this.result.get("doc.html").getText();
    TestUpdateHtmlFile.<String, String>operator_doubleArrow(_text, "<p>Hello 1</p>");
  }
  
  @Override
  protected void step3_updateNodes() {
    this.source.select("./html").setValue("<p>Hello 1 and Hello 2 are an amazing team.</p>");
  }
  
  @Override
  protected void step4_assertFilesAfterUpdate() {
    String _text = this.result.get("doc.html").getText();
    TestUpdateHtmlFile.<String, String>operator_doubleArrow(_text, "<p>Hello 1 and Hello 2 are an amazing team.</p>");
  }
  
  @Override
  protected void step5_updateFiles() {
    FileItem _get = this.result.get("doc.html");
    _get.setText("And now for something different");
  }
  
  @Override
  protected void step6_assertNodesAfterUpdate() {
    Object _value = this.source.select("./html").get().value();
    TestUpdateHtmlFile.<Object, String>operator_doubleArrow(_value, "And now for something different");
  }
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertFalse(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertTrue(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void fail() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static void fail(final String message) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestUpdateHtmlFile is already defined in TestUpdateHtmlFile.java.");
  }
}
