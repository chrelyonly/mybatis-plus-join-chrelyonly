package com.github.chrelyonly.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.chrelyonly.base.MPJBaseMapper;
import com.github.chrelyonly.interfaces.MPJBaseJoin;

import java.util.List;
import java.util.Map;

/**
 * @author yulichang
 * @see IService
 */
@SuppressWarnings("unused")
public interface MPJJoinService<T> extends IService<T> {

    /**
     * 根据 Wrapper 条件，查询总记录数
     */
    default Long selectJoinCount(MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinCount(wrapper);
    }

    /**
     * 连接查询返回一条记录
     */
    default <DTO> DTO selectJoinOne(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinOne(clazz, wrapper);
    }

    /**
     * 连接查询返回集合
     */
    default <DTO> List<DTO> selectJoinList(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinList(clazz, wrapper);
    }

    /**
     * 连接查询返回集合并分页
     */
    default <DTO, P extends IPage<DTO>> P selectJoinListPage(P page, Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinPage(page, clazz, wrapper);
    }

    /**
     * 连接查询返回Map
     */
    default Map<String, Object> selectJoinMap(MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinMap(wrapper);
    }

    /**
     * 连接查询返回Map集合
     */
    default List<Map<String, Object>> selectJoinMaps(MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinMaps(wrapper);
    }

    /**
     * 连接查询返回Map集合并分页
     */
    default <P extends IPage<Map<String, Object>>> P selectJoinMapsPage(P page, MPJBaseJoin<T> wrapper) {
        return ((MPJBaseMapper<T>) getBaseMapper()).selectJoinMapsPage(page, wrapper);
    }
}
