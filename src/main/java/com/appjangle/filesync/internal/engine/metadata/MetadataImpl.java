package com.appjangle.filesync.internal.engine.metadata;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.internal.engine.metadata.v01.ItemXml;
import com.google.common.base.Objects;
import io.nextweb.Node;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class MetadataImpl implements Metadata {
  private final List<ItemMetadata> items;
  
  public MetadataImpl() {
    ArrayList<ItemMetadata> _newArrayList = CollectionLiterals.<ItemMetadata>newArrayList();
    this.items = _newArrayList;
  }
  
  public List<ItemMetadata> getChildren() {
    return this.items;
  }
  
  public ItemMetadata get(final String name) {
    final Function1<ItemMetadata, Boolean> _function = new Function1<ItemMetadata, Boolean>() {
      public Boolean apply(final ItemMetadata it) {
        String _name = it.name();
        return Boolean.valueOf(Objects.equal(_name, name));
      }
    };
    return IterableExtensions.<ItemMetadata>findFirst(this.items, _function);
  }
  
  public ItemMetadata get(final Node forNode) {
    final Function1<ItemMetadata, Boolean> _function = new Function1<ItemMetadata, Boolean>() {
      public Boolean apply(final ItemMetadata it) {
        String _uri = it.uri();
        String _uri_1 = forNode.uri();
        return Boolean.valueOf(Objects.equal(_uri, _uri_1));
      }
    };
    return IterableExtensions.<ItemMetadata>findFirst(this.items, _function);
  }
  
  public Metadata add(final ItemMetadata itemMetadata) {
    MetadataImpl _xblockexpression = null;
    {
      final ItemXml item = new ItemXml();
      this.items.add(item);
      _xblockexpression = this;
    }
    return _xblockexpression;
  }
  
  public Metadata update(final ItemMetadata itemMetadata) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Metadata remove(final String name) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
