package com.core;

import com.utils.Const;
import com.utils.Util;
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
        MyRc myRc = new MyRc(rc);
        SqlSession s = MybatisHelper.ins().getSession();
        try {
            biz(myRc,s);
            s.commit();
        } catch (Exception e) {
            s.rollback();
            logger.error("",e);
        } finally {
            s.close();
        }

        if (myRc.getStatus()==200) {
            rc.put(Const.RESP,myRc.getRespData());
            rc.next();
        } else {
            Util.handlerFail(rc,myRc.getStatus());
        }
    }

    /**
     * 业务逻辑，省去事务代码
     * 实现类中不要catch异常，否则SQL无法回滚
     * 实现类不要用异步方法
     */
    abstract protected void biz(MyRc myRc, SqlSession s) throws Exception;

}
