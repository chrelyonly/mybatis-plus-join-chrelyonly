package com.github.chrelyonly.base;

import com.github.chrelyonly.base.service.MPJDeepService;
import com.github.chrelyonly.base.service.MPJJoinService;

/**
 * 基础service
 * 目前包含两个模块 连表查询 和 关系映射
 *
 * @author yulichang
 * @see MPJJoinService
 * @see MPJDeepService
 */
public interface MPJBaseService<T> extends MPJJoinService<T>, MPJDeepService<T> {
}
