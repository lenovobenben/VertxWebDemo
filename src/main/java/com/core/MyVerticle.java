package com.core;

import com.biz.MyRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        MyRouter.initRouter(router);
        server.requestHandler(router::accept).listen(8888);
    }
}
