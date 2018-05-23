package com.ssm.chapter17.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {

	public static final String URL = "www.qulianghong.cn";
	public static final String PASSWORD = "qy0918yx";

	public void testJedis(Jedis jedis) {
		// ��¼��������
		int i = 0;
		try {
			long start = System.currentTimeMillis();
			while (true) {
				long end = System.currentTimeMillis();
				// һ����������
				if (end - start >= 1000) {
					break;
				}
				i++;
				jedis.set("testpool" + i, i + "");
			}
		} finally {// �ر�����
			jedis.close();
		}
		// ��ӡ1���ڶ�Redis�Ĳ�������
		System.out.println("redisÿ�������" + i + "��");
	}

	/**
	 * ��ȡJedis���ӳ� �����ӳ��л�ȡjedisʵ��Ч�ʸ���
	 *
	 * @return JedisPool
	 */
	public JedisPool getJedisPool() {
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		// ��������
		poolCfg.setMaxIdle(50);
		// ���������
		poolCfg.setMaxTotal(100);
		// ���ȴ�������
		poolCfg.setMaxWaitMillis(20000);
		// ʹ�����ô������ӳ�
		return new JedisPool(poolCfg, URL);
	}
}
