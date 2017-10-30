package com.biz;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;


public class MyRouter {

    public static void initRouter(Router r){
        initHandler(r,"/t",new OneHandler());
        // TODO 业务逻辑

    }





    public static void initHandler(Router r, String uri, Handler<RoutingContext> handler) {
        r.route(uri).blockingHandler(handler);
    }
}
