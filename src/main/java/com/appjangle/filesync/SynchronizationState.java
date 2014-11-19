package com.appjangle.filesync;

import io.nextweb.Node;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class SynchronizationState {
  /**
   * All nodes already synchronized
   */
  private final Set<String> synced;
  
  public boolean addSynced(final Node n) {
    String _uri = n.uri();
    return this.synced.add(_uri);
  }
  
  public SynchronizationState() {
    HashSet<String> _hashSet = new HashSet<String>();
    this.synced = _hashSet;
  }
}
