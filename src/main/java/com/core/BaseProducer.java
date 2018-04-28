package com.core;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public abstract class BaseProducer implements IProducer {
    protected Future<JsonObject> future;

    public BaseProducer(Future future) {
        this.future = future;
    }

}
