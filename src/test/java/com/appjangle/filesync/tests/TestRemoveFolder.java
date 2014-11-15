package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckUpdatesTemplate;
import de.mxro.file.FileItem;
import de.oehme.xtend.junit.JUnit;
import io.nextweb.Node;
import io.nextweb.Query;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestRemoveFolder extends CheckUpdatesTemplate {
  protected void step1_defineData() {
    this.source.append("folder1", "./folder1");
    this.source.append("folder2", "./folder2");
    this.source.append("folder3", "./folder3");
  }
  
  protected void step2_assertFiles() {
    List<FileItem> _children = this.result.getChildren();
    int _size = _children.size();
    TestRemoveFolder.<Integer, Integer>operator_doubleArrow(Integer.valueOf(_size), Integer.valueOf(4));
    FileItem _child = this.result.getChild("folder1");
    boolean _exists = _child.exists();
    TestRemoveFolder.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists), Boolean.valueOf(true));
  }
  
  protected void step3_updateNodes() {
    Query _select = this.source.select("./folder1");
    this.source.remove(_select);
  }
  
  protected void step4_assertFilesAfterUpdate() {
    FileItem _child = this.result.getChild("folder1");
    boolean _exists = _child.exists();
    TestRemoveFolder.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_exists), Boolean.valueOf(false));
  }
  
  protected void step5_updateFiles() {
    this.result.deleteFolder("folder2");
  }
  
  protected void step6_assertNodesAfterUpdate() {
    Query _select = this.source.select("./html");
    Node _get = _select.get();
    Object _value = _get.value();
    TestRemoveFolder.<Object, String>operator_doubleArrow(_value, "And now for something different");
  }
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final long expected, final long actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    Assert.assertEquals(expected, actual, delta);
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    Assert.assertEquals(expected, actual, delta);
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    Assert.assertEquals(message, expected, actual, delta);
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    Assert.assertEquals(message, expected, actual, delta);
  }
  
  private static void assertFalse(final boolean condition) {
    Assert.assertFalse(condition);
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    Assert.assertFalse(message, condition);
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    Assert.assertNotEquals(first, second);
  }
  
  private static void assertNotEquals(final long first, final long second) {
    Assert.assertNotEquals(first, second);
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    Assert.assertNotEquals(message, first, second);
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    Assert.assertNotEquals(message, first, second);
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    Assert.assertNotEquals(first, second, delta);
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    Assert.assertNotEquals(message, first, second, delta);
  }
  
  private static void assertNotNull(final Object object) {
    Assert.assertNotNull(object);
  }
  
  private static void assertNotNull(final String message, final Object object) {
    Assert.assertNotNull(message, object);
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    Assert.assertNotSame(unexpected, actual);
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    Assert.assertNotSame(message, unexpected, actual);
  }
  
  private static void assertNull(final Object object) {
    Assert.assertNull(object);
  }
  
  private static void assertNull(final String message, final Object object) {
    Assert.assertNull(message, object);
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    Assert.assertSame(expected, actual);
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    Assert.assertSame(message, expected, actual);
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    Assert.<T>assertThat(actual, matcher);
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    Assert.<T>assertThat(reason, actual, matcher);
  }
  
  private static void assertTrue(final boolean condition) {
    Assert.assertTrue(condition);
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    Assert.assertTrue(message, condition);
  }
  
  private static void fail() {
    Assert.fail();
  }
  
  private static void fail(final String message) {
    Assert.fail(message);
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    Assert.assertEquals(expected, actual);
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    try {
    	block.apply();
    	Assert.fail("Expected a " + expected.getName());
    } catch (Exception e) {
    	Class<?> actual = e.getClass();
    	Assert.assertTrue(
    		"Expected a " + expected.getName() + " but got " + actual.getName(), 
    		expected.isAssignableFrom(actual)
    	);
    }
  }
}
