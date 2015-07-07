package com.appjangle.filesync;

import io.nextweb.Node;
import io.nextweb.Client;

@SuppressWarnings("all")
public interface NetworkOperationContext {
  public abstract Client session();
  
  public abstract Node parent();
}
