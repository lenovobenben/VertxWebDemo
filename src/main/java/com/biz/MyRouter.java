package com.biz;

import com.core.RouterHelper;


public class MyRouter {

    public static void initRouter(){
        RouterHelper.ins().mapDb("/t",new MyHandler1());

        RouterHelper.ins().map("/t2",new MyHandler2());

        RouterHelper.ins().mapBlock("/t3",new MyHandler3());

        // TODO 业务逻辑

    }

}
