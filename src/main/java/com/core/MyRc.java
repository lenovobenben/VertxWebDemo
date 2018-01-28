package com.core;

import io.vertx.core.MultiMap;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.RoutingContext;

import java.util.Set;

/**
 * 类似 RoutingContext
 */
public class MyRc {
    /**
     * 请求数据，只支持 POST
     */
    private String reqData;

    /**
     * 响应数据
     */
    private String respData;

    /**
     * 请求 header
     */
    private MultiMap reqHeaders;

    /**
     * 响应 header
     */
    private MultiMap respHeaders;

    /**
     * cookies
     */
    private Set<Cookie> cookies;

    private int status;

    public MyRc(RoutingContext rc){
        this.reqData = rc.getBodyAsString();
        this.reqHeaders = rc.request().headers();
        this.respHeaders = rc.response().headers();
        this.cookies = rc.cookies();
        this.status = 200;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }

    public MultiMap getReqHeaders() {
        return reqHeaders;
    }

    public MultiMap getRespHeaders() {
        return respHeaders;
    }

    public Set<Cookie> getCookies() {
        return cookies;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
