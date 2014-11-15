package com.appjangle.filesync.tests;

import com.appjangle.filesync.tests.CheckFilesTempalte;
import de.mxro.file.FileItem;
import de.mxro.file.Jre.FilesJre;
import java.util.List;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class TestSimpleNode extends CheckFilesTempalte {
  public void defineData() {
    this.source.append("A Folder");
  }
  
  public void assertFiles() {
    FileItem _wrap = FilesJre.wrap(this.target);
    List<FileItem> _children = _wrap.getChildren();
    InputOutput.<List<FileItem>>println(_children);
  }
}
