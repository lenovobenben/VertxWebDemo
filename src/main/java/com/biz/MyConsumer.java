package com.biz;

import com.core.BaseConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyConsumer extends BaseConsumer {

    private Logger logger = LoggerFactory.getLogger(MyConsumer.class);

    @Override
    public String addr() {
        return "ME";
    }

    @Override
    public JsonObject consumer0(JsonObject req) {
        logger.info(req.encode());
        return req.put("v2","GOOGLE");
    }

}
