package com.core;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * mybatis 的简单封装
 *
 * @author lihd
 */
public class MybatisHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    SqlSessionFactory sf;
    private boolean isInit = false;

    private MybatisHelper() {
    }

    private static MybatisHelper mybatisHelper = new MybatisHelper();

    public static MybatisHelper ins() {
        return mybatisHelper;
    }

    public void init() {
        if (isInit) {// 防止重复初始化
            return;
        }
        isInit = true;
        InputStream is = this.getClass().getClassLoader()
                .getResourceAsStream("mybatis.xml");
        if (is!=null) {
            sf = new SqlSessionFactoryBuilder()
                    .build(is);
            logger.info("MybatisHelper inited.............");
        } else {// 不需要DB功能
            logger.info("mybatisXmlNotExist! ");
        }
    }

    /**
     * 打开一个新的session
     *
     * @return
     */
    public SqlSession getSession() {
        return sf.openSession(false);
    }

    public SqlSessionFactory getSf() {
        return sf;
    }

}
