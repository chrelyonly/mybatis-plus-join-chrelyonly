package com.github.chrelyonly.toolkit;

import com.github.chrelyonly.query.MPJQueryWrapper;
import com.github.chrelyonly.wrapper.MPJLambdaWrapper;

/**
 * Wrapper 条件构造
 *
 * @author yulichang
 */
@SuppressWarnings("unused")
public class MPJWrappers {

    /**
     * MPJWrappers.<UserDO>queryJoin()
     */
    public static <T> MPJQueryWrapper<T> queryJoin() {
        return new MPJQueryWrapper<>();
    }

    /**
     * MPJWrappers.<UserDO>lambdaJoin()
     */
    public static <T> MPJLambdaWrapper<T> lambdaJoin() {
        return new MPJLambdaWrapper<>();
    }
}
