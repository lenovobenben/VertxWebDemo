package com.core;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public abstract class BaseConsumer implements IConsumer {
    @Override
    public final void consumer(Message<JsonObject> message) {
        JsonObject ret = consumer0(message.body());
        message.reply(ret);
    }

    public abstract JsonObject consumer0 (JsonObject req);
}
