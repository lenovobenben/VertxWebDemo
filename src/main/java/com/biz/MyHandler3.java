package com.biz;

import com.core.BaseDbHandler;
import com.core.JedisClient;
import com.core.MyRc;
import com.pojo.UserInfo;
import com.utils.Util;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.JedisPool;


/**
 * 业务代码示例
 */
public class MyHandler3 implements Handler<RoutingContext> {

    private final Logger logger = LoggerFactory.getLogger(MyHandler3.class);

    @Override
    public void handle(RoutingContext rc) {
        JedisClient.ins().set("testRedis",new JsonObject().put("STATUS",7).encode());
        String data = JedisClient.ins().get("testRedis");
        rc.response().end(data);
    }
}
