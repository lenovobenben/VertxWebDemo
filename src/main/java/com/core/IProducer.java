package com.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * 服务调用方接口
 * 本质是 消息生产者
 */
public interface IProducer {

    /**
     * 服务地址
     */
    String addr();

    /**
     * RPC 回调后的逻辑
     */
    void handler (AsyncResult<Message<JsonObject>> ar);
}
