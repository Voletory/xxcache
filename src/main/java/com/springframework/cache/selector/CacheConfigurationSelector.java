package com.springframework.cache.selector;

import com.springframework.cache.annotation.EnableCache;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * @author steven.zhu 2020/3/26 14:43.
 * @类描述：
 */
public class CacheConfigurationSelector extends AdviceModeImportSelector<EnableCache> {
    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        return new String[0];
    }
}
