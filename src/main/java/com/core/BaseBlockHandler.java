package com.core;

import com.utils.Const;
import com.utils.Util;
import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by lihd on 2016/12/28.
 */
public abstract class BaseBlockHandler implements Handler<RoutingContext>{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public final void handle(RoutingContext rc) {

        if (!Thread.currentThread().getName().contains("worker")) {
            logger.error("Not running in worker thread !");
            System.exit(0);// 代码有问题！直接退出 JVM
        }

        MyRc myRc = new MyRc(rc);
        try {
            biz(myRc);
        } catch (Exception e) {
            logger.error("",e);
            myRc.setStatus(500);
        }

        if (myRc.getStatus()==200) {
            rc.put(Const.RESP,myRc.getRespData());
            rc.next();
        } else {
            Util.handlerFail(rc,myRc.getStatus());
        }
    }

    /**
     * 实现类不要用异步方法
     */
    abstract protected void biz(MyRc myRc) throws Exception;

}
