package com.platform.soft.api;

import java.util.*;
import java.util.concurrent.TimeUnit;

public interface IRedisRepository<K, V> {


    /////////////////////////////////////key的操作//////////////////////////////////////////

    /**
     * 删除key
     *
     * @param key
     */
    void del(K key);

    /**
     * 删除Collection集合 keys
     *
     * @param keys
     */
    void del(Collection<K> keys);

    /**
     * key 是否存在
     *
     * @param key
     * @return true 存在 false 不存在
     */
    Boolean exists(K key);

    /**
     * 为给定 key 设置过期时间
     *
     * @param key
     * @param timeout  时间
     * @param timeUtit 时间单位
     * @return true 成功 false失败
     */
    Boolean expire(K key, long timeout, TimeUnit timeUtit);

    /**
     * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。
     * 不同在于 EXPIREAT 命令接受的时间参数是
     * UNIX 时间戳(unix timestamp)
     *
     * @param key
     * @param date
     * @return
     */
    Boolean expireAt(K key, Date date);

    /**
     * 以毫秒为单位返回 key 的剩余过期时间
     *
     * @param key
     * @return 当 key 不存在时，返回 -2<br>
     * 当 key 存在但没有设置剩余生存时间时，返回 -1<br>
     * 否则，以毫秒为单位，返回 key 的剩余生存时间<br>
     */
    Long pttl(K key);

    /**
     * 命令用于查找所有符合给定模式 pattern 的 key
     *
     * @param pattern
     * @return 符合给定模式的 key 列表 (Array)。
     */
    Set<K> keys(K pattern);

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key
     * @return <B>none (key不存在)<B><br>
     * <B> string (字符串)<B></B><br>
     * <B>list (列表)<B><br>
     * <B>set (集合)<B><br>
     * <B>zset (有序集)<B><br>
     * <B>hash (哈希表)<B><br>
     */
    String type(K key);


    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中。
     * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，
     * 或者 key 不存在于当前数据库，那么 MOVE 没有任何效果。
     * 因此，也可以利用这一特性，将 MOVE 当作锁(locking)原语(primitive)。
     *
     * @param key
     * @param dbIndex
     * @return 移动成功返回 true ，失败则返回 false
     */
    Boolean move(K key, int dbIndex);


    /////////////////////////////////////key的操作//////////////////////////////////////////


    /////////////////////////////////////String的操作//////////////////////////////////////////


    /**
     * 设置给定 key 的值。如果 key
     * 已经存储其他值， SET 就覆写旧值，且无视类型
     *
     * @param key
     * @param value
     * @return void
     */
    void set(K key, V value);

    /**
     * 设置key值,指定失效时间,还有时间单位
     *
     * @param key
     * @param value
     * @param timeout  失效时间
     * @param timeUnit 时间单位
     * @return void
     */
    void set(K key, V value, long timeout, TimeUnit timeUnit);

    /**
     * 根据key值获取存储的value值
     *
     * @param key
     * @return 当 key 不存在时，返回 null ，否则，返回 key 的值。如果 key 不是字符串类型返回null
     */
    V get(K key);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     * 当 key 存在但不是字符串类型时，返回null。
     * 当key不存在 怎返回 null
     *
     * @param key
     * @param value
     * @return <P>返回给定 key 的旧值。</P>
     * <P>当 key 没有旧值时，也即是， key 不存在时，返回 null</P>
     */
    V getSet(K key, V value);

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     *
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度。
     */
    Integer append(K key, String value);






    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     * 将 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内
     * 使用场景 计数器 限速器 参考 http://redisdoc.com/string/incr.html#incr
     *
     * @param key
     * @return 新值
     */
    Long incr(K key);

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     *
     * @param key
     * @param value
     * @return 新值
     * @since >= 2.6.0
     */
    Long incrBy(K key, long value);

    /**
     * 为 key 中所储存的值加上浮点数增量 increment
     * 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作
     * 如果命令执行成功，那么 key 的值会被更新为（执行加法之后的）新值，并且新值会以字符串的形式返回给调用者
     *
     * @param key
     * @param value
     * @return
     */
    Long incrByFloat(K key, long value);

    /**
     * 返回所有(一个或多个)给定 key 的值。
     * 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。
     * 因此，该命令永不失败。
     *
     * @param keys
     * @return
     */
    List<V> mGet(K... keys);

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
     * 如果 key 已经存在， SETEX 命令将覆写旧值
     * 原子性(atomic)操作
     *
     * @param key
     * @param timeout 过期时长  单位seconds (以秒为单位
     * @param value
     * @return 成功返回 1 失败返回 0
     */
    int setEx(K key, long timeout, V value);

    /**
     * 返回存在的key 的length 不存在返回0
     *
     * @param key
     * @return 存在返回 length 不存在返回 0
     */
    long strLen(K key);

    /////////////////////////////////////String的操作end//////////////////////////////////////////


    /////////////////////////////////////hash的操作(哈希表）//////////////////////////////////////////

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key   redis 的key
     * @param field hash 的key
     * @return 被成功移除的域的数量，不包括被忽略的域。
     */
    int hDel(K key, K field);

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param hk    hash 中的key
     * @param value
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     * 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0
     */
    int hSet(K key, K hk, V value);

    /**
     * 查看哈希表 key 中，给定域 field 是否存在
     *
     * @param key
     * @param hk
     * @return 1 exist 1,0 not exsist key or hk
     */
    int hExists(K key, K hk);

    /**
     * 返回redis 中的hash 对象
     *
     * @param key
     * @return
     */
    Map<K, V> hGet(K key);

    /**
     * 返回哈希表 key 中给定域 field 的值
     *
     * @param key
     * @param hk
     * @return
     */
    V hGet(K key, K hk);

    /**
     * 查询出全部的keys
     *
     * @param key
     * @return
     */
    Set<K> hKeys(K key);

    /**
     * 返回 key 中 hash的大小
     *
     * @param key
     * @return 没有key 则返回 0  否则返回hash 的大小
     */
    Long hLen(K key);

    /**
     * 不存在怎操作成功返回 1  存在 不进行操作返回0
     *
     * @param key
     * @param hk
     * @param value
     * @return int 1 设置成功,0 存在不进行设置操作
     */
    int hSetNX(K key, K hk, V value);


    /////////////////////////////////////hash的操作(哈希表）end //////////////////////////////////////////

    /////////////////////////////////////////list的操作/////////////////////////////////////////////////

    /**
     * 返回列表 key 中，下标为 index 的元素
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，
     * 以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推
     *
     * @param key
     * @param index
     * @return 列表中下标为 index 的元素
     */
    V lIndex(K key, int index);

    /**
     * 返回列表 key 的长度
     *
     * @param key
     * @return list 大小
     */
    Long lLeng(K key);

    /**
     * 移除并返回列表 key 的头元素
     *
     * @param key
     * @return 列表的头元素 当 key 不存在时，返回 null
     */
    V lPop(K key);

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素
     * 假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，
     * 这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    List<V> lRange(K key, int start, int stop);


    /**
     * @param key
     * @param index
     * @param value
     * @return 成功返回1  失败返回 0
     */
    int lSet(K key, int index, V value);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * @param key
     * @return 列表的尾元素  当 key 不存在时，返回 null
     */
    V rPop(K key);


    /**
     * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
     * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做
     *
     * @return
     */
    int rPushx(K key, V value);


}
