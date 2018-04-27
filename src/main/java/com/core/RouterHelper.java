package com.core;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RouterHelper {

    private static RouterHelper routerHelper = new RouterHelper();

    public static RouterHelper ins() {
        return routerHelper;
    }

    private RouterHelper() {
    }


    private Router router;

    public void init(Router router) {
        this.router = router;
    }

    /**
     * 设置一个阻塞的 handler 比如读写文件，redis 访问等
     */
    public void mappingBHandler(String uri, Handler<RoutingContext> handler) {
        router.route(uri).blockingHandler(handler, false);
    }

    /**
     * 设置一个阻塞的 handler ，串行化
     */
    public void mappingOrderedBHandler(String uri, Handler<RoutingContext> handler) {
        router.route(uri).blockingHandler(handler, true);
    }

    /**
     * 设置一个 DB handler ，访问 mysql
     */
    public void mappingDbHandler(String uri, BaseDbHandler handler) {
        router.route(uri).blockingHandler(handler, false);
    }

    /**
     * 设置一个 DB handler ，串行化
     */
    public void mappingOrderedDbHandler(String uri, BaseDbHandler handler) {
        router.route(uri).blockingHandler(handler, true);
    }

    /**
     * 设置一个非阻塞的 handler ， PS ： 千万不能执行耗时操作！
     */
    public void mappingHandler(String uri, BaseHandler handler) {
        router.route(uri).handler(handler);
    }

}
