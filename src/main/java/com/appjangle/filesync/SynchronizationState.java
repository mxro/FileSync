package com.appjangle.filesync;

import java.util.HashSet;
import java.util.Set;

/**
 * Must be synchronized.
 */
@SuppressWarnings("all")
public class SynchronizationState {
  /**
   * All nodes already synchronized
   */
  private final Set<String> synced;
  
  public synchronized boolean addSynced(final /* Node */Object n) {
    throw new Error("Unresolved compilation problems:"
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nuri cannot be resolved");
  }
  
  public synchronized boolean wasSynced(final /* Node */Object n) {
    throw new Error("Unresolved compilation problems:"
      + "\nuri cannot be resolved");
  }
  
  public SynchronizationState() {
    HashSet<String> _hashSet = new HashSet<String>();
    this.synced = _hashSet;
  }
}
