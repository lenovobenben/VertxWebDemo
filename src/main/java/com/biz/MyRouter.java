package com.biz;

import com.utils.Util;
import io.vertx.ext.web.Router;


public class MyRouter {

    public static void initRouter(Router r){
        Util.initDbHandler(r,"/t",new OneHandler());

        Util.initHandler(r,"/t2",new One2Handler());

        // TODO 业务逻辑

    }

}
