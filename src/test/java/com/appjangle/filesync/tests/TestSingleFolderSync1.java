package com.appjangle.filesync.tests;

import com.appjangle.jre.AppjangleJre;
import de.mxro.fn.Success;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import org.junit.Test;

@SuppressWarnings("all")
public class TestSingleFolderSync1 {
  @Test
  public void test() {
    final LocalServer testServer = AppjangleJre.startServer();
    NextwebPromise<Success> _shutdown = testServer.shutdown();
    _shutdown.get();
  }
}
