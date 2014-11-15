package com.appjangle.filesync.internal.engine.metadata.v01;

import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class NodesXml implements Serializable {
  public final List<ItemXml> items;
  
  public NodesXml() {
    ArrayList<ItemXml> _newArrayList = CollectionLiterals.<ItemXml>newArrayList();
    this.items = _newArrayList;
  }
}
