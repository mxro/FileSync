package com.appjangle.filesync.internal.engine;

import io.nextweb.Link;
import io.nextweb.Session;

@SuppressWarnings("all")
public class N {
  public static String HTML_VALUE() {
    return "https://admin1.linnk.it/types/v01/isHtmlValue";
  }
  
  public Link HTML_VALUE(final Session session) {
    return session.link("https://admin1.linnk.it/types/v01/isHtmlValue");
  }
  
  public static String TEMPLATE() {
    return "https://u1.linnk.it/6wbnoq/Types/aTemplate";
  }
  
  public static String TEXT_VALUE() {
    return "https://u1.linnk.it/6wbnoq/Types/aTextValue";
  }
  
  public static String LABEL() {
    return "https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel";
  }
  
  public Link LABEL(final Session session) {
    return session.link("https://u1.linnk.it/qc8sbw/usr/apps/textsync/files/shortLabel");
  }
}
