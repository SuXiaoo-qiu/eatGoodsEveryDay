package com.joeworld.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public class RedisUtils {
	final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);


//	@Autowired
//    private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	// Key（键），简单的key-value操作

	/**
	 * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
	 * 
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		logger.info("redis--------------key:"+key+"-执行了ttl方法----------");
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * 实现命令：expire 设置过期时间，单位秒
	 * 
	 * @param key
	 * @return
	 */
	public void expire(String key, long timeout) {
		logger.info("redis--------------key:"+key+"--timeout"+timeout+"执行了expire方法----------");

		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 实现命令：INCR key，增加key一次
	 * 
	 * @param key
	 * @return
	 */
	public long incr(String key, long delta) {
		logger.info("redis--------------key:"+key+"--delta:"+delta+"执行了incr方法----------");
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
	 */
	public Set<String> keys(String pattern) {
		logger.info("redis--------------"+pattern+"-执行了keys方法----------");

		return redisTemplate.keys(pattern);
	}

	/**
	 * 实现命令：DEL key，删除一个key
	 * 
	 * @param key
	 */
	public void del(String key) {
		logger.info("redis--------------"+key+"-执行了expire方法----------");

		redisTemplate.delete(key);
	}

	// String（字符串）

	/**
	 * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		logger.info("redis--------------key:"+key+"--value:"+value+"执行了set方法----------");
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            （以秒为单位）
	 */
	public void set(String key, String value, long timeout) {
		logger.info("redis--------------"+key+"-执行了expire方法----------");

		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 实现命令：GET key，返回 key所关联的字符串值。
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		logger.info("redis--------------"+key+"-执行了expire方法----------");
		return (String)redisTemplate.opsForValue().get(key);
	}

	/**
	 * 批量查询，对应mget
	 * @param keys
	 * @return
	 */
	public List<String> mget(List<String> keys) {
		logger.info("redis--------------keys:"+keys+"-执行了mget方法----------");

		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 批量查询，管道pipeline
	 * @param keys
	 * @return
	 */
	public List<Object> batchGet(List<String> keys) {
		logger.info("redis--------------"+keys+"-执行了batchGet方法----------");


//		nginx -> keepalive
//		redis -> pipeline

		List<Object> result = redisTemplate.executePipelined(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection src = (StringRedisConnection)connection;

				for (String k : keys) {
					src.get(k);
				}
				logger.info("redis--------------"+keys+"-执行了batchGet方法 返回为空----------");

				return null;
			}
		});
		logger.info("redis--------------"+keys+"-执行了batchGet方法返回:"+result+"----------");

		return result;
	}


	// Hash（哈希表）

	/**
	 * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, Object value) {
		logger.info("redis--------------key"+key+"--field:"+field+"--value:"+value+"执行了hget方法----------");
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		logger.info("redis--------------key"+key+"--field:"+field+"执行了hget方法----------");

		return (String) redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, Object... fields) {
		logger.info("redis--------------key:"+key+"-fields:"+fields+"执行了hdel方法----------");

		redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hgetall(String key) {
		logger.info("redis--------------"+key+"-执行了hgetall方法----------");
		return redisTemplate.opsForHash().entries(key);
	}

	// List（列表）

	/**
	 * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
	 * 
	 * @param key
	 * @param value
	 * @return 执行 LPUSH命令后，列表的长度。
	 */
	public long lpush(String key, String value) {
		logger.info("redis--------------key:"+key+"--value:"+value+"-执行了lpush方法----------");
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 实现命令：LPOP key，移除并返回列表 key的头元素。
	 * 
	 * @param key
	 * @return 列表key的头元素。
	 */
	public String lpop(String key) {
		logger.info("redis--------------"+key+"-执行了lpop方法----------");
		return (String)redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
	 * 
	 * @param key
	 * @param value
	 * @return 执行 LPUSH命令后，列表的长度。
	 */
	public long rpush(String key, String value) {
		logger.info("redis--------------key:"+key+"--value:"+value+"-执行了rpush方法----------");
		return redisTemplate.opsForList().rightPush(key, value);
	}

}