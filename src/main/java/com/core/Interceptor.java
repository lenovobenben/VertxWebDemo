package com.core;

import com.utils.Const;
import com.utils.Util;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 类似三大框架的拦截器
 */
public class Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    public static void before(RoutingContext rc) {
        // 记录 handler 的开始执行时间
        rc.put(Const.START,System.currentTimeMillis());
        // 预置一个空串做返回结果
        rc.put(Const.RESP,"");

        logger.info("request: " + rc.getBodyAsString());

        rc.response().putHeader("Content-type", "text/plain;charset=utf-8");

        // POST 解密
        String plainText;
        try {
            plainText = new String(Base64.getDecoder().decode(rc.getBodyAsString()),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
            Util.handlerFail(rc,500);
            return ;
        }

        // 将解密后的数据设置到 body 中
        rc.setBody(Buffer.buffer(plainText));

        rc.next();
    }

    public static void after(RoutingContext rc) {
        // 加密数据
        String resp = rc.get(Const.RESP);
        try {
            resp = Base64.getEncoder().encodeToString(resp.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
            resp = "";
        }

        rc.put(Const.RESP,resp);
        // 结束 rc
        Util.handlerEnd(rc);
    }
}
