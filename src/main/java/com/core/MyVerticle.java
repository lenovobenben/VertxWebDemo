package com.core;

import com.biz.MyRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        // 初始化 mybatis
        MybatisHelper.ins().init();

        // 初始化 web
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create().setUploadsDirectory("web/pic").setBodyLimit(1000*1000));

        router.route().handler(Interceptor::before);

        MyRouter.initRouter(router);

        router.route().handler(Interceptor::after);


        server.requestHandler(router::accept).listen(8888);
    }
}
