package com.appjangle.filesync.engine;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.engine.FileUtils;
import com.appjangle.filesync.engine.metadata.Metadata;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncFolder {
  private final FileItem folder;
  
  private final Node node;
  
  private final Converter converter;
  
  public SyncFolder(final FileItem folder, final Node node) {
    this.folder = folder;
    this.node = node;
    this.converter = this.converter;
  }
  
  private Metadata metadata;
  
  public void doIt() {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The constructor FileToNetworkOperations(Node, FileItem, Metadata, Converter) is not applicable for the arguments (Node,FileItem,Metadata)");
  }
  
  public Object fullDownload() {
    return null;
  }
  
  @Extension
  private FileUtils fileUtils = new FileUtils();
}
