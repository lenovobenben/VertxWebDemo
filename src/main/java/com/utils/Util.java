package com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    private static ObjectMapper om = new ObjectMapper();

    static {
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object obj){
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("toJson",e);
        }

        return null;
    }


    public static void initBlockHandler(Router r, String uri, Handler<RoutingContext> handler) {
        r.route(uri).blockingHandler(handler,false);
    }

    public static void initHandler(Router r, String uri, Handler<RoutingContext> handler) {
        r.route(uri).handler(handler);
    }

    public static void handlerEnd(RoutingContext rc) {

        logger.info("response : " + rc.get(Const.RESP));

        if (rc.get(Const.START) != null) {
            long now = System.currentTimeMillis();

            long consume = now - (Long)rc.get(Const.START);
            if (consume > Const.WARN_TIME) {// handler 耗时预警
                logger.warn("url :  " + rc.request().absoluteURI()
                        + "   param : " + rc.getBodyAsString()
                        + "   consume : " + consume);
            }
        }

        rc.response().end((String)rc.get(Const.RESP));
    }

    public static void handlerFail (RoutingContext rc, int status) {

        if (rc.get(Const.START) != null) {
            long now = System.currentTimeMillis();

            long consume = now - (Long)rc.get(Const.START);
            if (consume > Const.WARN_TIME) {// handler 耗时预警
                logger.warn("url :  " + rc.request().absoluteURI()
                        + "   param : " + rc.getBodyAsString()
                        + "   consume : " + consume);
            }
        }

        rc.fail(status);
    }
}
