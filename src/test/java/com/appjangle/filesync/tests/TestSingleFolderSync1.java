package com.appjangle.filesync.tests;

import com.appjangle.filesync.FileSync;
import com.appjangle.jre.AppjangleJre;
import de.mxro.async.Deferred;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.file.Jre.FilesJre;
import de.mxro.fn.Success;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.common.LocalServer;
import io.nextweb.promise.NextwebPromise;
import java.io.File;
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
    final File testFolder = this.folder.newFolder("sync1");
    final Deferred<Success> _function = new Deferred<Success>() {
      public void get(final ValueCallback<Success> cb) {
        Node _get = data.get();
        FileSync.sync(testFolder, _get, cb);
      }
    };
    AsyncJre.<Success>waitFor(_function);
    FilesJre.wrap(testFolder);
    NextwebPromise<Success> _close = session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = server.shutdown();
    _shutdown.get();
  }
}
