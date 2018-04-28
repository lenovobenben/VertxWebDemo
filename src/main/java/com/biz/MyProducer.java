package com.biz;

import com.core.BaseProducer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyProducer extends BaseProducer {
    private Logger logger = LoggerFactory.getLogger(MyProducer.class);

    public MyProducer(Future future) {
        super(future);
    }

    @Override
    public String addr() {
        return "ME";
    }

    @Override
    public void callBack(AsyncResult<Message<JsonObject>> ar) {
        if (ar.succeeded()) {
            logger.info(ar.result().body().encode());
            JsonObject result = ar.result().body().put("v3","IBM");
            future.complete(result);
        }
    }
}
