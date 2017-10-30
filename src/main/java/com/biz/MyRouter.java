package com.biz;

import io.vertx.ext.web.Router;

public class MyRouter {
    public static void initRouter(Router r){
        r.route("/t").handler(rc->rc.response().end("success"));
    }
}
