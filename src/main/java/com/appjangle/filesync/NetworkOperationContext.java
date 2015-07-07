package com.appjangle.filesync;

import io.nextweb.Client;
import io.nextweb.Node;

@SuppressWarnings("all")
public interface NetworkOperationContext {
  public abstract Client session();
  
  public abstract Node parent();
}
