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
  
  private void findConverter(final FileItem forFileItem, final ValueCallback<Converter> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method NO_VALUE is undefined for the type ConverterCollection");
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
  
  private void findConverter(final Node forNode, final ValueCallback<Converter> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method NO_VALUE is undefined for the type ConverterCollection");
  }
  
  public void createNodes(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.createNodes(metadata, source, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(source, _embed);
  }
  
  public void update(final Metadata metadata, final FileItem source, final ValueCallback<List<NetworkOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.update(metadata, source, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(source, _embed);
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.deleteNodes(metadata, cachedFile, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(cachedFile, _embed);
  }
  
  public void createFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.createFiles(folder, metadata, source, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(source, _embed);
  }
  
  public void updateFiles(final FileItem folder, final Metadata metadata, final Node source, final ValueCallback<List<FileOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.updateFiles(folder, metadata, source, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(source, _embed);
  }
  
  public void removeFiles(final FileItem folder, final Metadata metadata, final ItemMetadata item, final ValueCallback<List<FileOperation>> cb) {
    final Closure<Converter> _function = new Closure<Converter>() {
      public void apply(final Converter converter) {
        converter.removeFiles(folder, metadata, item, cb);
      }
    };
    ValueCallback<Converter> _embed = Async.<Converter>embed(cb, _function);
    this.findConverter(item, _embed);
  }
}
