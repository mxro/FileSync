package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckFilesTempalte;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import java.util.List;

@SuppressWarnings("all")
public class TestSimpleNode extends CheckFilesTempalte {
  public void defineData() {
    this.source.append("A Folder");
  }
  
  public void assertFiles() {
    FileItem _wrap = FilesJre.wrap(this.target);
    final List<FileItem> children = _wrap.getChildren();
  }
}
