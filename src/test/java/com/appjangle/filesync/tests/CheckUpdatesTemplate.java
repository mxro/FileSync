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
      + "\nType mismatch: cannot convert from (ValueCallback<Success>)=>void to Operation<Object>"
      + "\nType mismatch: cannot convert from (ValueCallback<Success>)=>void to Operation<Object>"
      + "\nType mismatch: cannot convert from (ValueCallback<Success>)=>void to Operation<Object>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or use the closures in a more specific context.");
  }
}
