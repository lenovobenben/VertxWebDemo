package com.biz;

import com.core.IConsumer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MyConsumer implements IConsumer {

    private Logger logger = LoggerFactory.getLogger(MyConsumer.class);

    @Override
    public String addr() {
        return "ME";
    }

    @Override
    public void consumer(Message<JsonObject> message) {
        logger.info(message.body().encode());
        // 一定要有 reply ！
        message.reply(message.body().put("v2","GOOGLE"));
    }

}
