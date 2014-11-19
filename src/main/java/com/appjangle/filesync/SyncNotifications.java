package com.appjangle.filesync;

import de.mxro.file.FileItem;
import io.nextweb.Link;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class SyncNotifications {
  public void onInsufficientAuthorization(final FileItem inFolder, final Link forNode) {
    InputOutput.<String>println(((("insuf " + inFolder) + " ") + forNode));
  }
}
