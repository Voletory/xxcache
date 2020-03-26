package com.springframework.cache;

import com.alibaba.fastjson.JSONObject;
import com.springframework.cache.core.CacheManagerFactory;
import com.springframework.cache.enums.CacheParams;
import org.ehcache.sizeof.SizeOf;

import java.util.concurrent.TimeUnit;

/**
 * @author steven.zhu 2020/3/26 19:14.
 * @类描述：
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {
    protected String name;
    protected boolean useable;
    protected volatile long getCount;
    protected volatile long hitCount;
    protected volatile long putCount;
    protected volatile long removeCount;
    protected SizeOf sizeOf;

    public AbstractCache() {
        useable = true;
        name = CacheManagerFactory.DEFAULT_CACHE_MANAGER_NAME;
        sizeOf = SizeOf.newInstance();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean useable() {
        return useable;
    }

    public void enable() {
        this.useable = true;
    }

    public void disable() {
        this.useable = false;
    }

    @Override
    public String cacheName() {
        return name;
    }

    @Override
    public V get(K key) throws CacheRuntimeException {
        getCount++;
        V v = doGet(key);
        if (v != null) {
            hitCount++;
        }
        return v;
    }

    @Override
    public void put(K key, V value) throws CacheRuntimeException {
        put(key, value, 0l, null);
    }

    @Override
    public void put(K key, V value, long expiring, TimeUnit timeUnit) throws CacheRuntimeException {
        putCount++;
        doPut(key, value, expiring, timeUnit);
    }

    @Override
    public void remove(K key) throws CacheRuntimeException {
        removeCount++;
        doRemove(key);
    }

    @Override
    public JSONObject report() {
        JSONObject report = new JSONObject();
        report.put(CacheParams.CACHE_NAME.NAME, cacheName());
        JSONObject size = new JSONObject();
        size.put(CacheParams.SIZE_CAPACITY.NAME, getCapacity());
        size.put(CacheParams.SIZE_QUANTITY.NAME, getQuantity());
        long memorySize = 0l;
        Object valueOrSize = getCacheValueOrSize();
        if (valueOrSize instanceof Number) {
            memorySize = ((Number) valueOrSize).longValue();
        } else {
            memorySize = sizeOf.sizeOf(valueOrSize);
        }
        size.put(CacheParams.SIZE_MEMORY.NAME, DecimalFactory.createDecimal("0.## Kb").format(String.valueOf(memorySize / 1024)));
        report.put(CacheParams.SIZE.NAME, size);
        report.put(CacheParams.TOTAL_GET.NAME, getCount);
        report.put(CacheParams.TOTAL_PUT.NAME, putCount);
        report.put(CacheParams.TOTAL_REMOVE.NAME, removeCount);
        report.put(CacheParams.HIT_RATIO.NAME, DecimalFactory.createDecimal("##.##%").format(String.valueOf(getCount == 0 ? 1d : (hitCount / getCount))));
        return report;
    }

    /**
     * 关闭
     *
     * @return
     */
    public abstract void close();

    /**
     * 最大容量
     *
     * @return
     */
    protected abstract long getCapacity();

    /**
     * 当前数量
     *
     * @return
     */
    protected abstract long getQuantity();

    /**
     * 当前所有缓存的对象或对象所占空间(由子类实现空间计算)
     *
     * @return
     */
    protected abstract Object getCacheValueOrSize();

    protected abstract V doGet(K key) throws CacheRuntimeException;

    protected abstract void doPut(K key, V value, long expiring, TimeUnit timeUnit) throws CacheRuntimeException;

    protected abstract void doRemove(K key) throws CacheRuntimeException;
}
