package com.core;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by lihd on 2016/12/28.
 */
public abstract class BaseDbHandler implements Handler<RoutingContext>{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public final void handle(RoutingContext rc) {

        rc.response().putHeader("Content-type", "application/json;charset=utf-8");

        String ret = null;

        SqlSession s = MybatisHelper.ins().getSession();
        try {
            ret = biz(rc.getBodyAsString(),s);
            s.commit();
        } catch (Exception e) {
            s.rollback();
            logger.error("",e);
            rc.fail(500);
            return;
        } finally {
            s.close();
        }

        if (ret!=null) {
            rc.response().end(ret);
        } else {
            rc.fail(500);
        }
    }

    /**
     * 业务逻辑，省去事务代码
     * 实现类中不要catch异常，否则SQL无法回滚
     * 实现类不要用异步方法
     */
    abstract protected String biz(String postData, SqlSession s) throws Exception;

}
