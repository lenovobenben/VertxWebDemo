package com.biz;

import com.core.RpcHelper;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

/**
 * vertx 风格（即异步风格）的 handler 都写这里
 */
public class MyRawHandler {

    private Logger logger = LoggerFactory.getLogger(MyRawHandler.class);

    private static MyRawHandler myRawHandler = new MyRawHandler();

    private MyRawHandler() {
    }

    public static MyRawHandler ins() {
        return myRawHandler;
    }


    public void h1(RoutingContext rc) {

        Future<JsonObject> future = Future.future();
        JsonObject jsonObject = new JsonObject();
        logger.info(jsonObject);
        RpcHelper.ins().rpc(jsonObject.put("v1","APPLE"), new MyProducer(future));

        future.setHandler(t-> {
            logger.info(t.result().encode());
            rc.response().end(t.result().encode());
        });
    }

    // TODO
}
