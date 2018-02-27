package com.biz;

import com.utils.Util;
import io.vertx.ext.web.Router;


public class MyRouter {

    public static void initRouter(Router r){
        Util.initDbHandler(r,"/t",new MyHandler1());

        Util.initHandler(r,"/t2",new MyHandler2());

        // TODO 业务逻辑

    }

}
