package com.core;

import com.biz.MyRawHandler;
import com.biz.MyRouter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyVerticle extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(MyVerticle.class);
    @Override
    public void start() throws Exception {
        // 初始化 mybatis
        MybatisHelper.ins().init();

        // 初始化 redis
        MyRedisPool.ins().init();

        // 初始化 web
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/t4").handler(MyRawHandler.ins()::h1);


        RouterHelper.ins().init(router);

        router.route("/html/*").handler(StaticHandler.create());

        router.route().handler(BodyHandler.create().setUploadsDirectory("web/pic").setBodyLimit(1000*1000));

        router.route().handler(Interceptor::before);

        MyRouter.initRouter();

        router.route().handler(Interceptor::after);

        server.requestHandler(router::accept).listen(8888);
    }
}
