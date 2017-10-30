package com.biz;

import com.core.BaseDbHandler;
import io.vertx.core.json.JsonObject;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class OneHandler extends BaseDbHandler {
    @Override
    protected String biz(String postData, SqlSession s) throws Exception {
        Map<String,Object> data = s.selectOne("com.user.UserMapper.getUserInfo",1);
        return new JsonObject(data).encode();
    }
}
