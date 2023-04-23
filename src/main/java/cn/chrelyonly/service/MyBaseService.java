package cn.chrelyonly.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;
import com.github.yulichang.interfaces.MPJBaseJoin;

import java.util.List;
import java.util.Map;

/**
 * @author chrelyonly
 * 复杂service接口
 * mybatis-plus-join 专用
 */
public interface MyBaseService<T> extends MPJBaseService<T> {
    /**
     * 量表统计接口
     * @param wrapper 条件构造器
     * @return 返回统计结果
     * @author chrelyonly
     */
    @Override
    default Long selectJoinCount(MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinCount(wrapper);
    }
    /**
     * 连表返回一条数据
     * @param clazz 返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的数据
     * @author chrelyonly
     */
    @Override
    default <DTO> DTO selectJoinOne(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinOne(clazz, wrapper);
    }
    /**
     * 连表返回多条数据
     * @param clazz 返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的数据
     * @author chrelyonly
     */
    @Override
    default <DTO> List<DTO> selectJoinList(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinList(clazz, wrapper);
    }
    /**
     * 连表返回多条数据
     * @param page 分页数据
     * @param clazz 返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    default <DTO, P extends IPage<DTO>> P selectJoinListPage(P page, Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinListPage(page, clazz, wrapper);
    }
    /**
     * 连表返回一条数据用map装配
     * @param wrapper 条件构造器
     * @return 返回map类型的分页数据
     * @author chrelyonly
     */
    @Override
    default Map<String, Object> selectJoinMap(MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinMap(wrapper);
    }
    /**
     * 连表返回多条数据用list<map>装配
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    default List<Map<String, Object>> selectJoinMaps(MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinMaps(wrapper);
    }
    /**
     * 连表返回多条数据用list<map>装配
     * @param page 分页数据
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    default <P extends IPage<Map<String, Object>>> P selectJoinMapsPage(P page, MPJBaseJoin<T> wrapper) {
        return MPJBaseService.super.selectJoinMapsPage(page, wrapper);
    }
}

