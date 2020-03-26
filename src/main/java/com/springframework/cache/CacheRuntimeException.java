package com.springframework.cache;

/**
 * @author steven.zhu 2020/3/26 19:04.
 * @类描述： 缓存异常类
 */
public class CacheRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6613599522126934243L;

    public CacheRuntimeException() {
    }
    public CacheRuntimeException(String message,Throwable ex) {
        super(message, ex);
    }
    public CacheRuntimeException(Throwable ex) {
        super(ex);
    }
}
