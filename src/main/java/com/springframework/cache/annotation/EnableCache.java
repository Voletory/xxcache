package com.springframework.cache.annotation;

import com.springframework.cache.selector.CacheConfigurationSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author steven.zhu 2020/3/26 14:37.
 * @类描述：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheConfigurationSelector.class)
public @interface EnableCache {
    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483647;
}
