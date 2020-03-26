package com.springframework.cache;

import java.text.DecimalFormat;

/**
 * @author steven.zhu 2020/3/26 19:31.
 * @类描述：
 */
public class DecimalFactory {
    public static DecimalFormat createDecimal(String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat;
    }
}
