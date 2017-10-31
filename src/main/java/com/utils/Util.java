package com.utils;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Util {
    public static void initHandler(Router r, String uri, Handler<RoutingContext> handler) {
        r.route(uri).blockingHandler(handler);
    }
}
