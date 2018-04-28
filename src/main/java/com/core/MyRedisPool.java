package com.core;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * redis 连接池
 */
public class MyRedisPool {

    private static MyRedisPool myRedisPool = new MyRedisPool();

    private MyRedisPool() {
    }

    public static MyRedisPool ins() {
        return myRedisPool;
    }

    private JedisPool pool = null;

    private static Logger logger = LoggerFactory.getLogger(MyRedisPool.class);

    /**
     * 初始化
     */
    public void init() {

        InputStream inputStream = MyRedisPool.class.getClassLoader().getResourceAsStream("redis.properties");
        if (inputStream == null) {
            logger.info("redisPropertiesNotExist ! ");
            return;
        }

        try {

            Properties props = new Properties();
            props.load(inputStream);

            JedisPoolConfig config = new JedisPoolConfig();
            //设置池配置项值
            config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));

            //根据配置 实例化jedis池
            pool = new redis.clients.jedis.JedisPool(config, props.getProperty("redis.ip"), Integer.valueOf(props.getProperty("redis.port")), 10000);

            logger.info("redis init ...");
        } catch (Exception e) {
            logger.info("Jedis pool failed!", e);
        }
    }


    /**
     * 从池中拿一个
     */
    public Jedis getJedisObject() {
        try {
            return pool.getResource();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 放回池中
     */
    public void recycleJedisObject(Jedis jedis) {
        if (jedis==null) {
            return ;
        }

        try {
            pool.returnResourceObject(jedis);
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
