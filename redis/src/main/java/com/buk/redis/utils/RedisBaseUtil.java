package com.buk.redis.utils;

import io.lettuce.core.RedisException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Redis基础工具
 *
 * @author BuK
 * @see com.buk.example.utils.RedisUtilTest
 * @since 2020/08/22
 */
public class RedisBaseUtil<V> {

    private RedisTemplate<String, V> redisTemplate;

    public RedisBaseUtil(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    ======================= Common =======================

    /**
     * 过期时间
     *
     * @param key
     * @param expire
     */
    public void expire(String key, Long expire) {
        expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 过期时间
     *
     * @param key
     * @param expire
     * @param timeUnit
     */
    public void expire(String key, Long expire, TimeUnit timeUnit) {
        redisTemplate.expire(key, expire, timeUnit);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public Boolean key(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

//    ======================= String =======================

    /**
     * SET
     *
     * @param key
     * @param value
     */
    public void set(String key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * SET
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, V value, Long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * SET
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public void set(String key, V value, Long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    /**
     * GET
     *
     * @param key
     * @return
     */
    @Nullable
    public V get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * INCRBYFLOAT
     *
     * @param key
     * @param delta
     * @return
     */
    @Nullable
    public Double incr(String key, double delta) {
        if (delta < 0) {
            throw new RedisException("delta必须大于等于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * INCRBYFLOAT
     *
     * @param key
     * @param delta
     * @return
     */
    @Nullable
    public Double decr(String key, double delta) {
        if (delta < 0) {
            throw new RuntimeException("delta必须大于等于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * STRLEN
     *
     * @param key
     * @return 字符串的长度
     */
    @Nullable
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

//    ======================= List [队列／堆栈 链表可以从表头和表尾追加和移除元素] =======================

    /**
     * LPUSH
     *
     * @param key
     * @param value
     * @return 操作后列表的长度
     */
    @Nullable
    public void lPush(String key, V value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * RPUSH
     *
     * @param key
     * @param value
     * @return 操作后列表的长度
     */
    @Nullable
    public Long rPush(String key, V value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * LPUSH
     *
     * @param key
     * @param values
     * @return 操作后列表的长度
     */
    @Nullable
    public Long lPushList(String key, Collection<V> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * RPUSH
     *
     * @param key
     * @param values
     * @return 操作后列表的长度
     */
    @Nullable
    public Long rPushList(String key, Collection<V> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * LPOP
     *
     * @param key
     * @return 第一个元素的值 || 当键不存在时为nil
     */
    @Nullable
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * RPOP
     *
     * @param key
     * @return 最后一个元素的值 || 当键不存在时为nil
     */
    @Nullable
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * LINDEX
     *
     * @param key
     * @param index 索引 [index>=0时，0表头，1第二个元素]，[index<0时，-1表尾，-2倒数第二个元素]
     * @return 请求的元素 || 索引超出范围时的nil
     */
    @Nullable
    public Object lIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * LSET
     *
     * @param key
     * @param index
     * @param value
     */
    public void lSet(String key, Long index, V value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * LRANGE
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围内的元素列表
     */
    @Nullable
    public List<V> lRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * LLEN
     *
     * @param key
     * @return 列表的长度
     */
    @Nullable
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * LTRIM
     *
     * @param key
     * @param start
     * @param end
     */
    public void lTrim(String key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * LREM
     *
     * @param key
     * @param count count > 0：删除等于value从头到尾移动的元素。
     *              count < 0：删除等于value从尾部移动到头部的元素。
     *              count = 0：删除所有等于的元素value。
     *              例如，LREM list -2 "hello"将删除"hello"存储在列表中的最后两次出现 list。
     * @param value
     * @return 删除的元素数量
     */
    @Nullable
    public Long lRem(String key, Long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

//    ======================= Hash [等价于Java语言的HashMap] =======================

    /**
     * HSET
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hSet(String key, String hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * HMSET
     *
     * @param key
     * @param valueMap
     */
    public void hMSet(String key, Map<String, V> valueMap) {
        redisTemplate.opsForHash().putAll(key, valueMap);
    }

    /**
     * HGET
     *
     * @param key
     * @param hashKey
     * @return 与字段关联的值 || 当字段不存在于散列或键中时为nil不存在
     */
    @Nullable
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * HMGET
     *
     * @param key
     * @param hashKeys
     * @return 与给定字段关联的值列表，其顺序与请求的顺序相同
     */
    public List<Object> hMGet(String key, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * HKEYS
     *
     * @param key
     * @return 哈希中的字段列表 || key不存在时的空列表
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * HVALS
     *
     * @param key
     * @return
     */
    public List<Object> hValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * HGETALL
     *
     * @param key
     * @return 存储在散列中的字段及其值的列表 || key不存在时的空列表
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HEXISTS
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hExists(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * HLEN
     *
     * @param key
     * @return 哈希中的字段数
     */
    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * HSTRLEN
     *
     * @param key
     * @param hashKey
     * @return 与散列相关的值的字符串长度field || 当field散列key中不存在或根本不存在时为0
     */
    @Nullable
    public Long hStrLen(String key, String hashKey) {
        return redisTemplate.opsForHash().lengthOfValue(key, hashKey);
    }

    /**
     * HDEL
     *
     * @param key
     * @return 从哈希中删除的字段数，不包括指定但非现有字段
     */
    public Long hDel(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

//    ======================= Set =======================

    /**
     * SADD
     *
     * @param key
     * @param values
     */
    public void sAdd(String key, V... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * SMEMBERS
     *
     * @param key
     * @return 集合的所有元素
     */
    @Nullable
    public Set<V> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * SRANDMEMBER
     *
     * @param key
     * @return 返回带有随机选择元素
     */
    public V sRandMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * SRANDMEMBER
     *
     * @param key
     * @param count
     * @return 返回一个元素数组 || 当key不存在时返回一个空数组
     */
    @Nullable
    public List<V> sRandMember(String key, Long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * SISMEMBER
     *
     * @param key
     * @param value
     * @return 元素是否存在
     */
    @Nullable
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * SMOVE
     *
     * @param key
     * @param value
     * @param destKey
     * @return 元素是否移动成功
     */
    @Nullable
    public Boolean sMove(String key, V value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * SREM
     *
     * @param key
     * @param values
     * @return 从集合中删除的成员数，不包括非现有成员
     */
    @Nullable
    public Long sRem(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * SPOP
     *
     * @param key
     * @return 删除的元素 || nil何时key不存在
     */
    @Nullable
    public Object sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * SPOP
     *
     * @param key
     * @param count
     * @return 删除的元素列表 || nil何时key不存在
     */
    @Nullable
    public List<V> sPop(String key, Long count) {
        return redisTemplate.opsForSet().pop(key, count);
    }

//    ======================= ZSet =======================

    /**
     * ZADD
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    @Nullable
    public Boolean zAdd(String key, V value, Double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * ZINCRBY
     *
     * @param key
     * @param value
     * @param score
     * @return 新得分member（双精度浮点数），表示为字符串
     */
    @Nullable
    public Double zsDecBy(String key, V value, Double score) {
        return zIncrBy(key, value, 0 - score);
    }

    /**
     * ZINCRBY
     *
     * @param key
     * @param value
     * @param score
     * @return 新得分member（双精度浮点数），表示为字符串
     */
    @Nullable
    public Double zIncrBy(String key, V value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * ZSCORE
     *
     * @param key
     * @param value
     * @return 得分member（双精度浮点数），表示为字符串
     */
    @Nullable
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * ZRANK
     *
     * @param key
     * @param value
     * @return 如果member存在于有序集中，则整数回复：等级member，如果member在排序集中key不存在或不存在，则 批量字符串回复：nil
     */
    @Nullable
    public Long zRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * ZREVRANK
     *
     * @param key
     * @param value
     * @return 如果member存在于有序集中，则整数回复：等级member
     * 如果member在排序集中key不存在或不存在，则 批量字符串回复：nil
     */
    @Nullable
    public Long zRevRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * ZRANGE
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围内的元素列表（如果WITHSCORES给出选项，则可选择使用其得分）
     */
    public Set<V> zRange(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * ZRANGE
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围内的元素列表（如果WITHSCORES给出选项，则可选择使用其得分）
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> zRangeWithScores(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * ZREVRANGE
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围内的元素列表（可选择其分数）
     */
    @Nullable
    public Set<V> zRevRange(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * ZREVRANGE
     *
     * @param key
     * @param start
     * @param end
     * @return 指定范围内的元素列表（可选择其分数）
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> zRevRangeWithScore(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * ZRANGEBYSCORE
     *
     * @param key
     * @param min
     * @param max
     * @return 指定得分范围内的元素列表（可选择其得分）
     */
    @Nullable
    public Set<V> zRangeByScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * ZRANGEBYSCORE
     *
     * @param key
     * @param min
     * @param max
     * @return 指定得分范围内的元素列表（可选择其得分）
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> zRangeByScoreWithScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * ZREVRANGEBYSCORE
     *
     * @param key
     * @param min
     * @param max
     * @return 指定得分范围内的元素列表（可选择其得分）
     */
    @Nullable
    public Set<V> zRevRangeByScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * ZREVRANGEBYSCORE
     *
     * @param key
     * @param min
     * @param max
     * @return 指定得分范围内的元素列表（可选择其得分）
     */
    @Nullable
    public Set<ZSetOperations.TypedTuple<V>> zRevRangeByScoreWithScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * ZCARD
     *
     * @param key
     * @return 排序集的基数（元素数） || 0 如果key不存在
     */
    @Nullable
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * ZREM
     *
     * @param key
     * @param value
     * @return 从已排序集中删除的成员数，不包括非现有成员
     */
    @Nullable
    public Long zRem(String key, Object... value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * ZREMRANGEBYRANK
     *
     * @param key
     * @param start
     * @param end
     * @return 删除的元素数量
     */
    @Nullable
    public Long zRemRangByRank(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * ZREMRANGEBYSCORE
     *
     * @param key
     * @param min
     * @param max
     * @return 删除的元素数量
     */
    @Nullable
    public Long zRemRangByScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }
}
