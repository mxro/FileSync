package com.appjangle.filesync.tests;

import com.appjangle.jre.AppjangleJre;
import de.mxro.fn.Success;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("all")
public class TestSingleFolderSync1 {
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();
  
  @Test
  public void test() {
    final LocalServer server = AppjangleJre.startServer();
    final Session session = AppjangleJre.createSession(server);
    final Query data = session.seed(server);
    data.append("Just a node");
    NextwebPromise<Success> _commit = session.commit();
    _commit.get();
    NextwebPromise<Success> _close = session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = server.shutdown();
    _shutdown.get();
  }
}
