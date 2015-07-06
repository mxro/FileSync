package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckNodesToFilesTemplate;
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
      + "\ncommit cannot be resolved"
      + "\nget cannot be resolved"
      + "\ncommit cannot be resolved"
      + "\nget cannot be resolved");
  }
}
