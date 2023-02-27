package com.github.chrelyonly.wrapper.interfaces;

/**
 * on function
 *
 * @author yulichang
 * @since 1.1.8
 */
@FunctionalInterface
public interface WrapperFunction<T> {

    T apply(T wrapper);
}
