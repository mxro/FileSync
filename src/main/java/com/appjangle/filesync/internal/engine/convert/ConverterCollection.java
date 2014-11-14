package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.Converter;
import com.appjangle.filesync.FileOperation;
import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.file.FileItem;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
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
    final Closure2<Converter, ValueCallback<Boolean>> _function = new Closure2<Converter, ValueCallback<Boolean>>() {
      public void apply(final Converter c, final ValueCallback<Boolean> itmcb) {
        c.worksOn(node, itmcb);
      }
    };
    final Closure<List<Boolean>> _function_1 = new Closure<List<Boolean>>() {
      public void apply(final List<Boolean> res) {
        boolean _contains = res.contains(Boolean.valueOf(true));
        cb.onSuccess(Boolean.valueOf(_contains));
      }
    };
    ValueCallback<List<Boolean>> _embed = Async.<List<Boolean>>embed(cb, _function_1);
    Async.<Converter, Boolean>forEach(this.converters, _function, _embed);
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
