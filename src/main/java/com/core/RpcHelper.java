package com.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * 异步 RPC 的简单封装
 */
public class RpcHelper {
    private static RpcHelper rpcHelper = new RpcHelper();
    private RpcHelper(){}

    public static RpcHelper ins(){
        return rpcHelper;
    }

    private Vertx vertx;

    public void setVertx (Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * 注册服务
     */
    public void regService(String addr, Handler<Message<JsonObject>> handler) {
        this.vertx.eventBus().consumer(addr, handler);
    }

    /**
     * 远程调用
     */
    public void rpc (String addr, JsonObject req, Handler<AsyncResult<Message<JsonObject>>> replyHandler) {
        this.vertx.eventBus().send(addr, req, replyHandler);
    }

}
