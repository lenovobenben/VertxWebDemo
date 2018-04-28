package com.core;

import com.biz.MyConsumer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyVerticle2 extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(MyVerticle2.class);

    @Override
    public void start() throws Exception {

        RpcHelper.ins().setVertx(this.vertx);

        RpcHelper.ins().regService(new MyConsumer());
    }
}
