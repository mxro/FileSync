package com.appjangle.filesync;

import de.mxro.file.FileItem;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class SyncNotifications {
  private boolean printToStdOut = false;
  
  public void onInsufficientAuthorization(final FileItem inFolder, final /* Link */Object forNode) {
    if (this.printToStdOut) {
      String _plus = ("Insufficient authorization for node [" + forNode);
      String _plus_1 = (_plus + "] in folder [");
      String _plus_2 = (_plus_1 + inFolder);
      String _plus_3 = (_plus_2 + "]");
      InputOutput.<String>println(_plus_3);
    }
  }
  
  public void onNodeNotDefined(final /* Node */Object parent, final /* Link */Object node) {
    if (this.printToStdOut) {
      String _plus = ("Folder is not defined  [" + node);
      String _plus_1 = (_plus + "] in parent [");
      String _plus_2 = (_plus_1 + parent);
      String _plus_3 = (_plus_2 + "]");
      InputOutput.<String>println(_plus_3);
    }
  }
  
  public void onStartSynchronizing(final FileItem folder, final /* Node */Object node) {
    if (this.printToStdOut) {
    }
  }
  
  public void onFinishedSynchronizing(final FileItem folder, final /* Node */Object node) {
    if (this.printToStdOut) {
    }
  }
  
  public void onNodeSkippedBecauseItWasAlreadySynced(final FileItem folder, final /* Node */Object node) {
    if (this.printToStdOut) {
      String _plus = ("Node was already synchronized [" + node);
      String _plus_1 = (_plus + "] in folder [");
      String _plus_2 = (_plus_1 + folder);
      String _plus_3 = (_plus_2 + "]. Synchronziation skipped.");
      InputOutput.<String>println(_plus_3);
    }
  }
  
  @Pure
  public boolean isPrintToStdOut() {
    return this.printToStdOut;
  }
  
  public void setPrintToStdOut(final boolean printToStdOut) {
    this.printToStdOut = printToStdOut;
  }
}
