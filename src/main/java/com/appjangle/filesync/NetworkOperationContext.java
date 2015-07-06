package com.appjangle.filesync;

@SuppressWarnings("all")
public interface NetworkOperationContext {
  public abstract /* Session */Object session();
  
  public abstract /* Node */Object parent();
}
