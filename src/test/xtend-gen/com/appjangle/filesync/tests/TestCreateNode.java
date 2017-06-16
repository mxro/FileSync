package com.appjangle.filesync.tests;

import com.appjangle.api.Node;
import com.appjangle.filesync.internal.engine.N;
import com.appjangle.filesync.tests.CheckFilesToNodesTemplate;
import de.oehme.xtend.junit.Hamcrest;
import de.oehme.xtend.junit.JUnit;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Rule;
import org.junit.internal.ArrayComparisonFailure;
import org.junit.rules.ErrorCollector;

@JUnit
@Hamcrest
@SuppressWarnings("all")
public class TestCreateNode extends CheckFilesToNodesTemplate {
  @Override
  protected void step1_defineFiles() {
    this.source.assertFolder("Oh my test");
  }
  
  @Override
  protected void step2_assertNodes() {
    int _size = this.result.selectAll().get().size();
    TestCreateNode.<Integer, Integer>operator_doubleArrow(Integer.valueOf(_size), Integer.valueOf(1));
    final Node node = this.result.select("./Oh_my_test").get();
    Matcher<Object> _notNullValue = TestCreateNode.notNullValue();
    this.<Node>operator_doubleArrow(node, _notNullValue);
    Object _value = node.select(this.n.LABEL(node.client())).get().value();
    Matcher<Object> _equalTo = TestCreateNode.<Object>equalTo("Oh my test");
    this.<Object>operator_doubleArrow(_value, _equalTo);
  }
  
  @Extension
  private N n = new N();
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertFalse(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNull(final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertNull(final String message, final Object object) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertTrue(final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void fail() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static void fail(final String message) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  @Rule
  @Extension
  public final ErrorCollector _errorCollector /* Skipped initializer because of errors */;
  
  private <T extends Object> void operator_doubleArrow(final T object, final Matcher<? super T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Iterable<Matcher<? super T>> matchers) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T>... matchers) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T> first, final Matcher<? super T> second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T> first, final Matcher<? super T> second, final Matcher<? super T> third) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth, final Matcher<? super T> fifth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> allOf(final Matcher<? super T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth, final Matcher<? super T> fifth, final Matcher<? super T> sixth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> any(final Class<T> type) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Iterable<Matcher<? super T>> matchers) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<? super T>... matchers) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<T> first, final Matcher<? super T> second) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<T> first, final Matcher<? super T> second, final Matcher<? super T> third) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth, final Matcher<? super T> fifth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> AnyOf<T> anyOf(final Matcher<T> first, final Matcher<? super T> second, final Matcher<? super T> third, final Matcher<? super T> fourth, final Matcher<? super T> fifth, final Matcher<? super T> sixth) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<Object> anything() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<Object> anything(final String description) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <LHS extends Object> CombinableMatcher.CombinableBothMatcher<LHS> both(final Matcher<? super LHS> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<String> containsString(final String substring) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> describedAs(final String description, final Matcher<T> matcher, final Object... values) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <LHS extends Object> CombinableMatcher.CombinableEitherMatcher<LHS> either(final Matcher<? super LHS> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<String> endsWith(final String suffix) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> equalTo(final T operand) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <U extends Object> Matcher<Iterable<U>> everyItem(final Matcher<U> itemMatcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<Iterable<? super T>> hasItem(final T item) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<Iterable<? super T>> hasItem(final Matcher<? super T> itemMatcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<Iterable<T>> hasItems(final T... items) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<Iterable<T>> hasItems(final Matcher<? super T>... itemMatchers) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> instanceOf(final Class<?> type) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> is(final T value) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> is(final Matcher<T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> isA(final Class<T> type) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> not(final Matcher<T> matcher) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> not(final T value) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<Object> notNullValue() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> notNullValue(final Class<T> type) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<Object> nullValue() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> nullValue(final Class<T> type) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> sameInstance(final T target) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static Matcher<String> startsWith(final String prefix) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
  
  private static <T extends Object> Matcher<T> theInstance(final T target) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe type TestCreateNode is already defined in TestCreateNode.java.");
  }
}
