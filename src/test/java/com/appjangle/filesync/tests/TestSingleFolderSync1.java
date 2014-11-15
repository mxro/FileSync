package com.appjangle.filesync.tests;

import com.appjangle.jre.AppjangleJre;
import de.mxro.fn.Success;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import org.junit.Test;

@SuppressWarnings("all")
public class TestSingleFolderSync1 {
  @Test
  public void test() {
    final LocalServer server = AppjangleJre.startServer();
    final Session session = AppjangleJre.createSession(server);
    NextwebPromise<Success> _close = session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = server.shutdown();
    _shutdown.get();
  }
}
