package com.appjangle.filesync.jre;

import java.io.File;

import com.appjangle.filesync.FileSync;

import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import io.nextweb.Node;

@SuppressWarnings("all")
public class FileSyncJre {
  public static void sync(final File folder, final Node node) {
    final Operation<Object> _function = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> cb) {
        FileItem _wrap = FilesJre.wrap(folder);
        FileSync.sync(_wrap, node, cb);
      }
    };
    Async.<Object>waitFor(_function);
  }
}
