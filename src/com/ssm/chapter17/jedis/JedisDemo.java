package com.ssm.chapter17.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {

	public static final String URL = "www.qulianghong.cn";
	public static final String PASSWORD = "qy0918yx";

	public void testJedis(Jedis jedis) {
		// 记录操作次数
		int i = 0;
		try {
			long start = System.currentTimeMillis();
			while (true) {
				long end = System.currentTimeMillis();
				// 一秒后结束操作
				if (end - start >= 1000) {
					break;
				}
				i++;
				jedis.set("testpool" + i, i + "");
			}
		} finally {// 关闭连接
			jedis.close();
		}
		// 打印1秒内对Redis的操作次数
		System.out.println("redis每秒操作：" + i + "次");
	}

	/**
	 * 获取Jedis连接池 从连接池中获取jedis实例效率更好
	 *
	 * @return JedisPool
	 */
	public JedisPool getJedisPool() {
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		// 最大空闲数
		poolCfg.setMaxIdle(50);
		// 最大连接数
		poolCfg.setMaxTotal(100);
		// 最大等待毫秒数
		poolCfg.setMaxWaitMillis(20000);
		// 使用配置创建连接池
		return new JedisPool(poolCfg, URL);
	}
}
