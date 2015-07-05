package com.appjangle.filesync.tests;

import org.junit.Test;

@SuppressWarnings("all")
public abstract class CheckUpdatesTemplate extends CheckNodesToFilesTemplate {
  protected abstract void step3_updateNodes();
  
  protected abstract void step4_assertFilesAfterUpdate();
  
  protected abstract void step5_updateFiles();
  
  protected abstract void step6_assertNodesAfterUpdate();
  
  @Test
  @Override
  public void test() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method commit is undefined for the type CheckUpdatesTemplate"
      + "\nThe method commit is undefined for the type CheckUpdatesTemplate"
      + "\nget cannot be resolved"
      + "\nget cannot be resolved");
  }
}
