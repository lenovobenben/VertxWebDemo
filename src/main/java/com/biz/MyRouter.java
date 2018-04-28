package com.biz;

import com.core.RouterHelper;


public class MyRouter {

    public static void initRouter(){
        RouterHelper.ins().mappingDbHandler("/t",new MyHandler1());

        RouterHelper.ins().mappingHandler("/t2",new MyHandler2());

        RouterHelper.ins().mappingBHandler("/t3",new MyHandler3());

        // TODO 业务逻辑

    }

}
