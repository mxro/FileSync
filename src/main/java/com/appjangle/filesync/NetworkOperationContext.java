package com.appjangle.filesync;

import io.nextweb.Node;
import io.nextweb.Session;

@SuppressWarnings("all")
public interface NetworkOperationContext {
  public abstract Session session();
  
  public abstract Node parent();
}
