package com.biz;

import com.core.BaseBlockHandler;
import com.core.JedisClient;
import com.core.MyRc;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


/**
 * 业务代码示例
 */
public class MyHandler3 extends BaseBlockHandler {

    private final Logger logger = LoggerFactory.getLogger(MyHandler3.class);

    @Override
    protected void biz(MyRc myRc) throws Exception {
        JedisClient.ins().set("testRedis",new JsonObject().put("STATUS",7).encode());
        String data = JedisClient.ins().get("testRedis");
        myRc.setRespData(data);
    }
}
