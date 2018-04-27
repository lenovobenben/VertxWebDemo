package com.biz;

import com.core.RouterHelper;
import com.utils.Util;
import io.vertx.ext.web.Router;


public class MyRouter {

    public static void initRouter(){
        RouterHelper.ins().mappingDbHandler("/t",new MyHandler1());

        RouterHelper.ins().mappingHandler("/t2",new MyHandler2());

        // TODO 业务逻辑

    }

}
