package com.appjangle.filesync.internal.engine.convert;

import com.appjangle.filesync.ItemMetadata;
import com.appjangle.filesync.Metadata;
import com.appjangle.filesync.NetworkOperation;
import com.appjangle.filesync.internal.engine.FileUtils;
import com.appjangle.filesync.internal.engine.N;
import de.mxro.file.FileItem;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import io.nextweb.promise.NextwebOperation;
import io.nextweb.utils.data.NextwebDataExtension;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
  
  public boolean isTextType(final /* Link */Object link) {
    throw new Error("Unresolved compilation problems:"
      + "\nuri cannot be resolved");
  }
  
  public Object getFileExtension(final /* Node */Object forNode, final ValueCallback<String> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method exception is undefined for the type ConvertUtils"
      + "\nThe method contains is undefined for the type ConvertUtils"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nselectAllLinks cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  public void deleteNodes(final Metadata metadata, final ItemMetadata cachedFile, final ValueCallback<List<NetworkOperation>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nlink cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nhasDirectChild cannot be resolved"
      + "\nremoveSafeRecursive cannot be resolved"
      + "\nremoveSafe cannot be resolved");
  }
  
  public Object appendLabel(final /* Query */Object toNode, final String label) {
    throw new Error("Unresolved compilation problems:"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nLABEL cannot be resolved");
  }
  
  public List<NextwebOperation<?>> appendTypesAndIcon(final /* Query */Object toNode, final FileItem source) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from ArrayList<Object> to List<NextwebOperation<?>>"
      + "\nsession cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nHTML_VALUE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEMPLATE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nRICHTEXT cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEMPLATE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nJAVASCRIPT cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEMPLATE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nCOFFEESCRIPT cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEMPLATE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nCSS cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEMPLATE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTYPE cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nICON cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nappendSafe cannot be resolved"
      + "\nTEXT_VALUE cannot be resolved");
  }
  
  public final static Object NO_VALUE = new Object();
  
  public void getFileName(final /* Node */Object forNode, final FileItem inFolder, final String fileExtension, final ValueCallback<String> cb) {
    final Closure<String> _function = new Closure<String>() {
      @Override
      public void apply(final String fileNameFromNode) {
        String fileName = (fileNameFromNode + fileExtension);
        int idx = 1;
        while (inFolder.get(fileName).exists()) {
          {
            fileName = ((fileNameFromNode + Integer.valueOf(idx)) + fileExtension);
            idx++;
          }
        }
        cb.onSuccess(fileName);
      }
    };
    ValueCallback<String> _embed = AsyncCommon.<String>embed(cb, _function);
    this.getFileName(forNode, _embed);
  }
  
  public void getFileName(final /* Node */Object fromNode, final ValueCallback<String> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method exception is undefined for the type ConvertUtils"
      + "\nThe method value is undefined for the type ConvertUtils"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nuri cannot be resolved"
      + "\nselect cannot be resolved"
      + "\nsession cannot be resolved"
      + "\nlink cannot be resolved"
      + "\ncatchUndefined cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved");
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
