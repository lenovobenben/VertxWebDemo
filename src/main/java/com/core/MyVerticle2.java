package com.core;

import com.biz.RpcAddr;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyVerticle2 extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(MyVerticle2.class);

    @Override
    public void start() throws Exception {

        RpcHelper.ins().setVertx(this.vertx);

        RpcHelper.ins().regService(RpcAddr.TEST, message->{
            logger.info(message.body().encode());
            // 一定要有 reply ！
            message.reply(message.body().put("v2","GOOGLE"));
        });
    }
}
