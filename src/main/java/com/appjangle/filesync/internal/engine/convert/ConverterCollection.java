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
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void worksOn(final Node node, final ValueCallback<Boolean> cb) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
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
