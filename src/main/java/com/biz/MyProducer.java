package com.biz;

import com.core.IProducer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyProducer implements IProducer {
    private Logger logger = LoggerFactory.getLogger(MyProducer.class);

    Future<JsonObject> future;

    public MyProducer(Future future) {
        this.future = future;
    }

    @Override
    public String addr() {
        return "ME";
    }

    @Override
    public void handler(AsyncResult<Message<JsonObject>> ar) {
        if (ar.succeeded()) {
            logger.info(ar.result().body().encode());
            JsonObject result = ar.result().body().put("v3","IBM");
            future.complete(result);
        }
    }
}
