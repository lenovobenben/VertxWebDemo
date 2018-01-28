package com.biz;

import com.core.BaseDbHandler;
import com.core.MyRc;
import com.utils.Util;
import io.vertx.core.json.JsonObject;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * 业务代码示例
 */
public class OneHandler extends BaseDbHandler {
    @Override
    protected void biz(MyRc myRc, SqlSession s) throws Exception {
        // 获得 post 数据
        JsonObject jo = new JsonObject(myRc.getReqData());
        // 执行业务逻辑
        Map<String,Object> data = s.selectOne("com.user.UserMapper.getUserInfo",jo.getInteger("userId"));
        // 设置返回数据
        myRc.setRespData(Util.toJson(data));
    }
}
