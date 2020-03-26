package com.springframework.cache.core.config;

import com.springframework.cache.core.CacheManager;
import com.springframework.cache.core.DefaultCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven.zhu 2020/3/26 18:55.
 * @类描述：
 */
@Configuration
public class CacheConfiguration {

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager defaultCacheManager() {
        return new DefaultCacheManager();
    }
}
