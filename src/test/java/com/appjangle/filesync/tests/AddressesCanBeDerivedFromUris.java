package com.appjangle.filesync.tests;

import com.appjangle.filesync.engine.convert.ConvertUtils;
import org.junit.Test;

@SuppressWarnings("all")
public class AddressesCanBeDerivedFromUris {
  @Test
  public void test() {
    ConvertUtils.getNameFromUri("https://myuri.com/just/for/testing/name.xml");
  }
}
