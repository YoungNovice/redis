package com;

import com.ssm.chapter17.jedis.JedisDemo;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTester {

    @Test
    public void testBase() {
        Jedis jedis = new Jedis(JedisDemo.URL, 6379);
        JedisDemo jedisDemo = new JedisDemo();
        jedis.auth(JedisDemo.PASSWORD);
         jedisDemo.testJedis(jedis);
    }

    @Test
    public void testPool() {
        JedisDemo jedisDemo = new JedisDemo();
        JedisPool jedisPool =  jedisDemo.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.auth(JedisDemo.PASSWORD);
        jedisDemo.testJedis(jedis);
    }
}
