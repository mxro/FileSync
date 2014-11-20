package com.appjangle.filesync;

import de.mxro.file.FileItem;
import io.nextweb.Link;
import io.nextweb.Node;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class SyncNotifications {
  private boolean printToStdOut = false;
  
  public void onInsufficientAuthorization(final FileItem inFolder, final Link forNode) {
    if (this.printToStdOut) {
      InputOutput.<String>println((((("Insufficient authorization for node [" + forNode) + "] in folder [") + inFolder) + "]"));
    }
  }
  
  public void onNodeNotDefined(final Node parent, final Link node) {
    if (this.printToStdOut) {
      InputOutput.<String>println((((("Node is not defined  [" + node) + "] in parent [") + parent) + "]"));
    }
  }
  
  public void onStartSynchronizing(final FileItem folder, final Node node) {
    if (this.printToStdOut) {
      InputOutput.<String>println((((("[" + node) + "]->Start in [") + folder) + "]"));
    }
  }
  
  public void onFinishedSynchronizing(final FileItem folder, final Node node) {
    if (this.printToStdOut) {
      InputOutput.<String>println((((("[" + node) + "]->Finish in [") + folder) + "]"));
    }
  }
  
  public void onNodeSkippedBecauseItWasAlreadySynced(final FileItem folder, final Node node) {
    if (this.printToStdOut) {
      InputOutput.<String>println((((("Node was already synchronized [" + node) + "] in folder [") + folder) + "]. Synchronziation skipped."));
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
