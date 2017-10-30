package com.core;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Boot {
    public static void main(String[] args) {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
        final Logger logger = LoggerFactory.getLogger(Boot.class);
        logger.info("start......");
        // 部署verticle
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticle());
    }
}
