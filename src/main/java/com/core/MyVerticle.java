package com.core;

import com.biz.MyRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        // 初始化 mybatis
        MybatisHelper.ins().init();

        // 初始化 web
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        MyRouter.initRouter(router);
        server.requestHandler(router::accept).listen(8888);
    }
}
