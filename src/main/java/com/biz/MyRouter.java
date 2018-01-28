package com.biz;

import com.utils.Util;
import io.vertx.ext.web.Router;


public class MyRouter {

    public static void initRouter(Router r){
        Util.initBlockHandler(r,"/t",new OneHandler());
        // TODO 业务逻辑

    }

}
