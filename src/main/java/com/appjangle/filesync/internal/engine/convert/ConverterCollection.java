package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.async.callbacks.ValueCallback;
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
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method forEach is undefined for the type ConverterCollection"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nThe method contains is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method worksOn(Node, ValueCallback<Boolean>) is not applicable for the arguments (Node,Node,Object)"
      + "\nType mismatch: cannot convert from Node to ValueCallback<Boolean>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  private Object findConverter(final FileItem forFileItem, final ValueCallback<Converter> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method forEach is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nType mismatch: cannot convert from FileItem to ValueCallback<Boolean>"
      + "\nType mismatch: cannot convert from void to boolean"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  private void findConverter(final ItemMetadata forItem, final ValueCallback<Converter> cb) {
    for (final Converter c : this.converters) {
      Class<? extends Converter> _class = c.getClass();
      String _string = _class.toString();
      String _converter = forItem.converter();
      boolean _equals = _string.equals(_converter);
      if (_equals) {
        cb.onSuccess(c);
        return;
      }
    }
    String _converter_1 = forItem.converter();
    String _plus = (((("Cannot find converter for [" + forItem) + "].\n") + 
      "  Required Converter: ") + _converter_1);
    String _plus_1 = (_plus + "\n");
    String _plus_2 = (_plus_1 + 
      "  Defined Converters: ");
    String _plus_3 = (_plus_2 + this.converters);
    throw new RuntimeException(_plus_3);
  }
  
  private Object findConverter(final Node forNode, final ValueCallback<Converter> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method forEach is undefined for the type ConverterCollection"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method onSuccess is undefined for the type ConverterCollection"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method worksOn(Node, ValueCallback<Boolean>) is not applicable for the arguments (Node,Node,Object)"
      + "\nType mismatch: cannot convert from Node to ValueCallback<Boolean>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method createNodes(Metadata, FileItem, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (Metadata,Metadata,FileItem,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Metadata to FileItem"
      + "\nType mismatch: cannot convert from FileItem to ValueCallback<List<NetworkOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method update(Metadata, FileItem, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (Metadata,Metadata,FileItem,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Metadata to FileItem"
      + "\nType mismatch: cannot convert from FileItem to ValueCallback<List<NetworkOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method deleteNodes(Metadata, ItemMetadata, ValueCallback<List<NetworkOperation>>) is not applicable for the arguments (Metadata,Metadata,ItemMetadata,ValueCallback<List<NetworkOperation>>)"
      + "\nType mismatch: cannot convert from Metadata to ItemMetadata"
      + "\nType mismatch: cannot convert from ItemMetadata to ValueCallback<List<NetworkOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method createFiles(FileItem, Metadata, Node, ValueCallback<List<FileOperation>>) is not applicable for the arguments (FileItem,FileItem,Metadata,Node,ValueCallback<List<FileOperation>>)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to Node"
      + "\nType mismatch: cannot convert from Node to ValueCallback<List<FileOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method updateFiles(FileItem, Metadata, Node, ValueCallback<List<FileOperation>>) is not applicable for the arguments (FileItem,FileItem,Metadata,Node,ValueCallback<List<FileOperation>>)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to Node"
      + "\nType mismatch: cannot convert from Node to ValueCallback<List<FileOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method embed is undefined for the type ConverterCollection"
      + "\nInvalid number of arguments. The method removeFiles(FileItem, Metadata, ItemMetadata, ValueCallback<List<FileOperation>>) is not applicable for the arguments (FileItem,FileItem,Metadata,ItemMetadata,ValueCallback<List<FileOperation>>)"
      + "\nType mismatch: cannot convert from FileItem to Metadata"
      + "\nType mismatch: cannot convert from Metadata to ItemMetadata"
      + "\nType mismatch: cannot convert from ItemMetadata to ValueCallback<List<FileOperation>>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
}
