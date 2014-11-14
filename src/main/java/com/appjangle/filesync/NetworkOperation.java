package com.appjangle.filesync;

import com.appjangle.filesync.NetworkOperationContext;
import de.mxro.fn.Function;
import io.nextweb.promise.NextwebPromise;
import java.util.List;

@SuppressWarnings("all")
public interface NetworkOperation extends Function<NetworkOperationContext, List<NextwebPromise<?>>> {
}
