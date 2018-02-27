package com.biz;

import com.core.BaseHandler;
import com.core.MyRc;

import java.util.Date;

/**
 * 业务代码示例 2
 */
public class MyHandler2 extends BaseHandler {
    @Override
    protected void biz(MyRc myRc) throws Exception {
        myRc.setRespData(new Date().toString());
    }
}
