package cn.chrelyonly.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.interfaces.MPJBaseJoin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chrelyonly
 * mapper接口类
 * 连表插件需继承我
 */
public interface MyBaseMapper<T> extends MPJBaseMapper<T> {
    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param wrapper joinWrapper
     * @return 总记录数
     */
    @Override
    Long selectJoinCount(@Param("ew") MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回一条记录
     *
     * @param clazz   resultType
     * @param wrapper joinWrapper
     * @return DTO
     */
    @Override
    <DTO> DTO selectJoinOne(@Param("resultTypeClass_Eg1sG") Class<DTO> clazz,@Param("ew")  MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回Map
     *
     * @param wrapper joinWrapper
     * @return Map
     */
    @Override
    Map<String, Object> selectJoinMap(@Param("ew") MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回记录集合
     *
     * @param clazz   resultType
     * @param wrapper joinWrapper
     * @return DTO集合
     */
    @Override
    <DTO> List<DTO> selectJoinList(@Param("resultTypeClass_Eg1sG") Class<DTO> clazz,@Param("ew")  MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回Map集合
     *
     * @param wrapper joinWrapper
     * @return Map集合
     */
    @Override
    List<Map<String, Object>> selectJoinMaps(@Param("ew") MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回记录集合并分页
     *
     * @param page   分页数据
     * @param clazz   resultType
     * @param wrapper joinWrapper
     * @return DTO集合
     */
    @Override
    <DTO, P extends IPage<DTO>> P selectJoinPage(P page,@Param("resultTypeClass_Eg1sG") Class<DTO> clazz,@Param("ew")  MPJBaseJoin<T> wrapper);

    /**
     * 连表查询返回Map集合并分页
     *
     * @param page
     * @param wrapper joinWrapper
     * @return Map集合
     */
    @Override
    <P extends IPage<Map<String, Object>>> P selectJoinMapsPage(P page, MPJBaseJoin<T> wrapper);
}
