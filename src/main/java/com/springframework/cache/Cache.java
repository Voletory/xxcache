package com.springframework.cache;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * @author steven.zhu 2020/3/26 19:04.
 * @类描述：
 */
public interface Cache<K, V> {

    /** 加入缓存 */
    public void put(K key, V value) throws CacheRuntimeException;

    /** 加入缓存，并设定过期时间 */
    public void put(K key, V value, long expiring, TimeUnit timeUnit) throws CacheRuntimeException;

    /** 从缓存删除 */
    public void remove(K key) throws CacheRuntimeException;

    /** 从缓存读取 */
    public V get(K key) throws CacheRuntimeException;

    /** 是否存在 */
    public boolean exists(K key);

    /** 清空缓存 */
    public void clear() throws CacheRuntimeException;

    /** 是否开启 */
    public boolean useable();

    /** 缓存使用情况报告 */
    public JSONObject report();

    public String cacheName();
}
