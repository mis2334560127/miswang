package com.example.passport.redis;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Map;
import java.util.Set;

/**
 * Created by CPR210 on 2016/5/31.
 */
public interface RedisFactory {

  Long ttl(String key);

  String get(String key);

  /**
   * <p>向redis存入key和value,并释放连接资源</p>
   * <p>如果key已经存在 则覆盖</p>
   *
   * @param key
   * @param value
   *
   * @return 成功 返回OK 失败返回 0
   */
  String set(String key, String value);


  /**
   * 通过key 对value进行加值+1操作,
   * 当value不是int类型时会返回错误,
   * 当key不存在是则value为1
   * @param key
   * @return
   */
  Long incr(String key);

  Long decr(String key);

  /**
   * <p>设置key value并制定这个键值的有效期</p>
   *
   * @param key
   * @param value
   * @param seconds 单位:秒
   *
   * @return 成功返回OK 失败和异常返回null
   */
  String setex(String key, int seconds, String value);

  /**
   * 通过key同时设置 hash的多个field
   * @param key
   * @param hash
   * @return
   */
  String hmset(String key, Map<String, String> hash);


  Long zadd(String key, double score, String data);

  Set<String> zrevrange(String key, int start, int end);


  /**
   * <p>删除指定的key,也可以传入一个包含key的数组</p>
   *
   * @param keys 一个key  也可以使 string 数组
   *
   * @return 返回删除成功的个数
   */
  Long del(String... keys);

  ScanResult<String> sscan(String cursor, ScanParams params);

  public void unLock(String key);


  /**
   * 获取集合中元素个数
   * @param key
   * @return
   */
  Long zcard(String key);

  /**
   * 删除索引区间内的元素
   * @param key
   * @param start
   * @param end
   * @return
   */
  Long zremrangeByRank(String key, int start, int end);

  /**
   * 获取项在zset中的索引
   * 可判断集合中某值是否存在
   * @param key
   * @param param  值
   * @return
   */
  Long zrank(String key, String param);

  /**
   * 用于移除有序集中的一个或多个成员，不存在的成员将被忽略
   * 格式：zrem  zset的key 项的值，项的值可以是多个
   * @param key
   * @param sysMsgId
   * @return
   */
  Long zrem(String key, String... sysMsgId);

  /**
   * 删除指定key中的指定数据  By  最大（sysScoreMax）与最小（sysScoreMin）范围
   * @param key
   * @param sysScoreMin 开始范围包括开始
   * @param sysScoreMax 结束范围包括结束
   * @return
   */
  Long zremRangeByScore(String key, String sysScoreMin, String sysScoreMax);

  /**
   * 统计指定key中的指定数据  By  最大（sysScoreMax）与最小（sysScoreMin）范围 内的总数
   * @param key
   * @param sysScoreMin 开始范围包括开始
   * @param sysScoreMax 结束范围包括结束
   * @return
   */
  Long countKeyByScore(String key, String sysScoreMin, String sysScoreMax);


}
