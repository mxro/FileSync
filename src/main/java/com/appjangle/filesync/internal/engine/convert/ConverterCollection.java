package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.file.FileItem;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ConverterCollection implements Converter {
  private final List<Converter> converters;
  
  public ConverterCollection() {
    ArrayList<Converter> _arrayList = new ArrayList<Converter>();
    this.converters = _arrayList;
  }
  
  public boolean addConverter(final Converter converter) {
    return this.converters.add(converter);
  }
  
  public boolean worksOn(final FileItem source) {
    boolean _xblockexpression = false;
    {
      boolean res = false;
      for (final Converter c : this.converters) {
        boolean _or = false;
        if (res) {
          _or = true;
        } else {
          boolean _worksOn = c.worksOn(source);
          _or = _worksOn;
        }
        res = _or;
      }
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public void worksOn(final Node node, final /* ValueCallback<Boolean> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type ConverterCollection"
      + "\nThe method contains is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method worksOn(Node, ValueCallback) is not applicable for the arguments (Node,Node,Object)"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nforEach cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  private Object findConverter(final FileItem forFileItem, final /* ValueCallback<Converter> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nType mismatch: cannot convert from void to boolean"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nforEach cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonFailure cannot be resolved");
  }
  
  private void findConverter(final ItemMetadata forItem, final /* ValueCallback<Converter> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonSuccess cannot be resolved");
  }
  
  private Object findConverter(final Node forNode, final /* ValueCallback<Converter> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type ConverterCollection"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method worksOn(Node, ValueCallback) is not applicable for the arguments (Node,Node,Object)"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nforEach cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonFailure cannot be resolved");
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method createNodes(Metadata, FileItem, ValueCallback) is not applicable for the arguments (Metadata,Metadata,FileItem,ValueCallback)"
      + "\nType mismatch: cannot convert from Metadata to FileItem"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
  
  public void update(final Metadata metadata, final FileItem source, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method update(Metadata, FileItem, ValueCallback) is not applicable for the arguments (Metadata,Metadata,FileItem,ValueCallback)"
      + "\nType mismatch: cannot convert from Metadata to FileItem"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method deleteNodes(Metadata, ItemMetadata, ValueCallback) is not applicable for the arguments (Metadata,Metadata,ItemMetadata,ValueCallback)"
      + "\nType mismatch: cannot convert from Metadata to ItemMetadata"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method createFiles(FileItem, Metadata, Node, ValueCallback) is not applicable for the arguments (FileItem,FileItem,Metadata,Node,ValueCallback)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to Node"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method updateFiles(FileItem, Metadata, Node, ValueCallback) is not applicable for the arguments (FileItem,FileItem,Metadata,Node,ValueCallback)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to Node"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final /* ValueCallback<List<FileOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The method removeFiles(FileItem, Metadata, ItemMetadata, ValueCallback) is not applicable for the arguments (FileItem,FileItem,Metadata,ItemMetadata,ValueCallback)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to ItemMetadata"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved");
  }
}
