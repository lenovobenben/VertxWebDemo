package com.core;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import redis.clients.jedis.Jedis;

public class JedisClient {

    private static Logger logger = LoggerFactory.getLogger(JedisClient.class);

    private JedisClient() {
    }

    private static JedisClient jedisClient = new JedisClient();

    public static JedisClient ins() {
        return jedisClient;
    }


    public String get(String key) {
        Jedis jedis = MyRedisPool.ins().getJedisObject();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        } finally {
            MyRedisPool.ins().recycleJedisObject(jedis);
        }
    }

    public void set(String key, String value, int liveTime) {
        Jedis jedis = MyRedisPool.ins().getJedisObject();
        try {
            jedis.set(key, value);
            jedis.expire(key, liveTime);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            MyRedisPool.ins().recycleJedisObject(jedis);
        }
    }

    public void set(String key, String value) {
        Jedis jedis = MyRedisPool.ins().getJedisObject();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            MyRedisPool.ins().recycleJedisObject(jedis);
        }
    }


    public void lpush(String key, String value) {
        Jedis jedis = MyRedisPool.ins().getJedisObject();
        try {
            jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            MyRedisPool.ins().recycleJedisObject(jedis);
        }
    }

    public void del(String key) {
        Jedis jedis = MyRedisPool.ins().getJedisObject();
        try {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            MyRedisPool.ins().recycleJedisObject(jedis);
        }

    }

}
