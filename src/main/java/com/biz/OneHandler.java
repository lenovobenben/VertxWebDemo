package com.biz;

import com.core.BaseDbHandler;
import com.utils.Util;
import io.vertx.core.json.JsonObject;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class OneHandler extends BaseDbHandler {
    @Override
    protected String biz(String postData, SqlSession s) throws Exception {
        JsonObject jo = new JsonObject(postData);
        Map<String,Object> data = s.selectOne("com.user.UserMapper.getUserInfo",jo.getInteger("userId"));
        return Util.toJson(data);
    }
}
