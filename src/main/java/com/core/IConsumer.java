package com.core;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * 服务提供者接口
 * 本质是 消息消费者
 */
public interface IConsumer {

    /**
     * 服务地址
     */
    String addr();

    /**
     * 服务处理逻辑
     */
    void consumer(Message<JsonObject> message);
}
