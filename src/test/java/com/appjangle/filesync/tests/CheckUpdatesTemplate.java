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
  public void test() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Async is undefined for the type CheckUpdatesTemplate"
      + "\nThe method or field Async is undefined for the type CheckUpdatesTemplate"
      + "\nThe method or field Async is undefined for the type CheckUpdatesTemplate"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nwaitFor cannot be resolved"
      + "\nwaitFor cannot be resolved"
      + "\nwaitFor cannot be resolved");
  }
}
