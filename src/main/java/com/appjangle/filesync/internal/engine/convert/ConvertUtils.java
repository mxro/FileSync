package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
import com.google.common.base.Objects;
import de.mxro.file.FileItem;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.NextwebOperation;
import io.nextweb.utils.data.NextwebDataExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ConvertUtils {
  private final List<String> labelTypes = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(N.LABEL(), N.LABEL2(), N.LABEL3()));
  
  private final Map<String, String> textValueExtensions = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(Pair.<String, String>of(N.HTML_VALUE(), ".html"), Pair.<String, String>of(N.TYPE(), ".type"), Pair.<String, String>of(N.CSS(), ".css"), Pair.<String, String>of(N.JAVASCRIPT(), ".js"), Pair.<String, String>of(N.COFFEESCRIPT(), ".coffee"), Pair.<String, String>of(N.RICHTEXT(), ".htm")));
  
  public boolean isTextValue(final String fileName) {
    boolean _xblockexpression = false;
    {
      final String ext = this.futils.getExtension(fileName);
      _xblockexpression = this.textValueExtensions.containsValue(("." + ext));
    }
    return _xblockexpression;
  }
  
  public boolean isTextType(final Link link) {
    Set<String> _keySet = this.textValueExtensions.keySet();
    String _uri = link.uri();
    return _keySet.contains(_uri);
  }
  
  public void getFileExtension(final Node forNode, final /* ValueCallback<String> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nonFailure cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public Object deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final /* ValueCallback<List<NetworkOperation>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public Query appendLabel(final Query toNode, final String label) {
    Query _appendSafe = toNode.appendSafe(label, "./.label");
    Session _session = toNode.session();
    Link _LABEL = this.n.LABEL(_session);
    return _appendSafe.appendSafe(_LABEL);
  }
  
  public List<NextwebOperation<?>> appendTypesAndIcon(final Query toNode, final FileItem source) {
    ArrayList<NextwebOperation<?>> _xblockexpression = null;
    {
      final ArrayList<NextwebOperation<?>> res = CollectionLiterals.<NextwebOperation<?>>newArrayList();
      final Session session = toNode.session();
      String ext = source.getExtension();
      ext = ("." + ext);
      boolean _equals = Objects.equal(ext, ".html");
      if (_equals) {
        Link _HTML_VALUE = this.n.HTML_VALUE(session);
        Query _appendSafe = toNode.appendSafe(_HTML_VALUE);
        res.add(_appendSafe);
        Link _TEMPLATE = this.n.TEMPLATE(session);
        Query _appendSafe_1 = toNode.appendSafe(_TEMPLATE);
        res.add(_appendSafe_1);
        final Query icon = toNode.appendSafe("https://appjangle.com/files/img/20141029/HTML.png", "./.icon");
        res.add(icon);
        Link _ICON = this.n.ICON(session);
        Query _appendSafe_2 = icon.appendSafe(_ICON);
        res.add(_appendSafe_2);
      } else {
        boolean _equals_1 = Objects.equal(ext, ".htm");
        if (_equals_1) {
          Link _RICHTEXT = this.n.RICHTEXT(session);
          Query _appendSafe_3 = toNode.appendSafe(_RICHTEXT);
          res.add(_appendSafe_3);
          Link _TEMPLATE_1 = this.n.TEMPLATE(session);
          Query _appendSafe_4 = toNode.appendSafe(_TEMPLATE_1);
          res.add(_appendSafe_4);
          final Query icon_1 = toNode.appendSafe("https://appjangle.com/files/img/20141119/RTF.png", "./.icon");
          res.add(icon_1);
          Link _ICON_1 = this.n.ICON(session);
          Query _appendSafe_5 = icon_1.appendSafe(_ICON_1);
          res.add(_appendSafe_5);
        } else {
          boolean _equals_2 = Objects.equal(ext, ".js");
          if (_equals_2) {
            Link _JAVASCRIPT = this.n.JAVASCRIPT(session);
            Query _appendSafe_6 = toNode.appendSafe(_JAVASCRIPT);
            res.add(_appendSafe_6);
            Link _TEMPLATE_2 = this.n.TEMPLATE(session);
            Query _appendSafe_7 = toNode.appendSafe(_TEMPLATE_2);
            res.add(_appendSafe_7);
            final Query icon_2 = toNode.appendSafe("https://appjangle.com/files/img/20141029/JavaScript.png", "./.icon");
            res.add(icon_2);
            Link _ICON_2 = this.n.ICON(session);
            Query _appendSafe_8 = icon_2.appendSafe(_ICON_2);
            res.add(_appendSafe_8);
          } else {
            boolean _equals_3 = Objects.equal(ext, ".coffee");
            if (_equals_3) {
              Link _COFFEESCRIPT = this.n.COFFEESCRIPT(session);
              Query _appendSafe_9 = toNode.appendSafe(_COFFEESCRIPT);
              res.add(_appendSafe_9);
              Link _TEMPLATE_3 = this.n.TEMPLATE(session);
              Query _appendSafe_10 = toNode.appendSafe(_TEMPLATE_3);
              res.add(_appendSafe_10);
              final Query icon_3 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Coffeescript.png", "./.icon");
              res.add(icon_3);
              Link _ICON_3 = this.n.ICON(session);
              Query _appendSafe_11 = icon_3.appendSafe(_ICON_3);
              res.add(_appendSafe_11);
            } else {
              boolean _equals_4 = Objects.equal(ext, ".css");
              if (_equals_4) {
                Link _CSS = this.n.CSS(session);
                Query _appendSafe_12 = toNode.appendSafe(_CSS);
                res.add(_appendSafe_12);
                Link _TEMPLATE_4 = this.n.TEMPLATE(session);
                Query _appendSafe_13 = toNode.appendSafe(_TEMPLATE_4);
                res.add(_appendSafe_13);
                final Query icon_4 = toNode.appendSafe("https://appjangle.com/files/img/20141118/CSS.png", "./.icon");
                res.add(icon_4);
                Link _ICON_4 = this.n.ICON(session);
                Query _appendSafe_14 = icon_4.appendSafe(_ICON_4);
                res.add(_appendSafe_14);
              } else {
                boolean _equals_5 = Objects.equal(ext, ".type");
                if (_equals_5) {
                  Link _TYPE = this.n.TYPE(session);
                  Query _appendSafe_15 = toNode.appendSafe(_TYPE);
                  res.add(_appendSafe_15);
                  final Query icon_5 = toNode.appendSafe("https://appjangle.com/files/img/20141118/Type.png", "./.icon");
                  res.add(icon_5);
                  Link _ICON_5 = this.n.ICON(session);
                  Query _appendSafe_16 = icon_5.appendSafe(_ICON_5);
                  res.add(_appendSafe_16);
                  final Query description = toNode.appendSafe("");
                  res.add(description);
                  Link _link = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2013/12/11/n9");
                  Query _appendSafe_17 = description.appendSafe(_link);
                  res.add(_appendSafe_17);
                }
              }
            }
          }
        }
      }
      Link _TEXT_VALUE = this.n.TEXT_VALUE(session);
      Query _appendSafe_18 = toNode.appendSafe(_TEXT_VALUE);
      res.add(_appendSafe_18);
      _xblockexpression = res;
    }
    return _xblockexpression;
  }
  
  public final static Object NO_VALUE = new Object();
  
  public void getFileName(final Node forNode, final FileItem inFolder, final String fileExtension, final /* ValueCallback<String> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from int to Iterable<?>"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public void getFileName(final Node fromNode, final /* ValueCallback<String> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field AsyncCommon is undefined for the type ConvertUtils"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ncollect cannot be resolved"
      + "\nembed cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\ncreateCallback cannot be resolved"
      + "\nonSuccess cannot be resolved"
      + "\nonFailure cannot be resolved"
      + "\nonSuccess cannot be resolved");
  }
  
  public static String getNameFromUri(final String uri) {
    int _lastIndexOf = uri.lastIndexOf("/");
    int _plus = (_lastIndexOf + 1);
    return uri.substring(_plus);
  }
  
  @Extension
  private N n = new N();
  
  @Extension
  private NextwebDataExtension ext = new NextwebDataExtension();
  
  @Extension
  private FileUtils futils = new FileUtils();
}
