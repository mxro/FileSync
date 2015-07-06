package com.appjangle.filesync.jre;

import com.appjangle.filesync.FileSync;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Success;
import java.io.File;

@SuppressWarnings("all")
public class FileSyncJre {
  public static void sync(final File folder, final /* Node */Object node) {
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> cb) {
        FileItem _wrap = FilesJre.wrap(folder);
        FileSync.sync(_wrap, node, cb);
      }
    };
    Async.<Success>waitFor(_function);
  }
}
