package com.github.chrelyonly.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chrelyonly.base.mapper.MPJDeepMapper;
import com.github.chrelyonly.base.mapper.MPJJoinMapper;

/**
 * @author yulichang
 * @see BaseMapper
 */
public interface MPJBaseMapper<T> extends MPJJoinMapper<T>, MPJDeepMapper<T> {


}
