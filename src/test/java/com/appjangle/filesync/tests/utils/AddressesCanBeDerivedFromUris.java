package com.appjangle.filesync.tests.utils;

import com.appjangle.filesync.internal.engine.convert.ConvertUtils;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class AddressesCanBeDerivedFromUris {
  @Test
  public void test() {
    Assert.assertEquals("name.xml", ConvertUtils.getNameFromUri("https://myuri.com/just/for/testing/name.xml"));
  }
}
