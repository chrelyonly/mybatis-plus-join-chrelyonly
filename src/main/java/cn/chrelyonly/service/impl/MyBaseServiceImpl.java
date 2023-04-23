package cn.chrelyonly.service.impl;


import cn.chrelyonly.mapper.MyBaseMapper;
import cn.chrelyonly.service.MyBaseService;
import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.interfaces.MPJBaseJoin;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 复杂service构造器
 * @author 11725
 * 用于简化频繁创建service的繁琐操作
 * 传入 mapper 和 entity, 生成service
 * M extends MPJBaseMapper<T>, T
 * 用法与 BaseServiceImpl 一致
 * mapper接口类需要继承 MPJBaseMapper 而不是 BaseMapper
 * 可重写任意接口
 */
public class MyBaseServiceImpl<M extends MyBaseMapper<T>, T extends BaseEntity> extends MPJBaseServiceImpl<M, T> implements MyBaseService<T> {
    /**
     * 核心方法
      * @param mClass mapper接口类
     */
    public MyBaseServiceImpl(Class<M> mClass){
        this.baseMapper = SpringUtil.getBean(mClass);
    }

//    连表接口
    /**
     * 量表统计接口
     *
     * @param wrapper 条件构造器
     * @return 返回统计结果
     * @author chrelyonly
     */
    @Override
    public Long selectJoinCount(MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinCount(wrapper);
    }

    /**
     * 连表返回一条数据
     *
     * @param clazz   返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的数据
     * @author chrelyonly
     */
    @Override
    public <DTO> DTO selectJoinOne(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinOne(clazz, wrapper);
    }

    /**
     * 连表返回多条数据
     *
     * @param clazz   返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的数据
     * @author chrelyonly
     */
    @Override
    public <DTO> List<DTO> selectJoinList(Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinList(clazz, wrapper);
    }

    /**
     * 连表返回多条数据
     *
     * @param page    分页数据
     * @param clazz   返回装配的对象
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    public <DTO, P extends IPage<DTO>> P selectJoinListPage(P page, Class<DTO> clazz, MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinListPage(page, clazz, wrapper);
    }

    /**
     * 连表返回一条数据用map装配
     *
     * @param wrapper 条件构造器
     * @return 返回map类型的分页数据
     * @author chrelyonly
     */
    @Override
    public Map<String, Object> selectJoinMap(MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinMap(wrapper);
    }

    /**
     * 连表返回多条数据用list<map>装配
     *
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    public List<Map<String, Object>> selectJoinMaps(MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinMaps(wrapper);
    }

    /**
     * 连表返回多条数据用list<map>装配
     *
     * @param page    分页数据
     * @param wrapper 条件构造器
     * @return 返回T类型的分页数据
     * @author chrelyonly
     */
    @Override
    public <P extends IPage<Map<String, Object>>> P selectJoinMapsPage(P page, MPJBaseJoin<T> wrapper) {
        return MyBaseService.super.selectJoinMapsPage(page, wrapper);
    }


    //    *******************************************************************************************************************************************************
    //    使接口同步(blade框架的service接口)  重写新增保存修改方法中携带,时间和用户信息字段
    /**
     * 重写save方法(自动填写时间和用户信息)
     * @param entity 实体对象
     */
    public boolean save(T entity) {
    this.resolveEntity(entity);
    return super.save(entity);
}

    /**
     * 自动填写时间和用户信息
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     * @return boolean
     */
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.saveBatch(entityList, batchSize);
    }

    /**
     * 自动填写时间和用户信息
     * @param entity 实体对象
     * @return boolean
     */
    public boolean updateById(T entity) {
        this.resolveEntity(entity);
        return super.updateById(entity);
    }

    /**
     * 自动填写时间和用户信息
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     * @return boolean
     */
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.updateBatchById(entityList, batchSize);
    }

    /**
     * 自动填写时间和用户信息
     * @param entity 实体对象
     * @return boolean
     */
    public boolean saveOrUpdate(T entity) {
        return entity.getId() == null ? this.save(entity) : this.updateById(entity);
    }

    /**
     * 自动填写时间和用户信息
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     * @return boolean
     */
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(this::resolveEntity);
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    /**
     * 自动填写时间和用户信息
     * @param entity 实体对象
     */
    private void resolveEntity(T entity) {
//        try {
//            BladeUser user = AuthUtil.getUser();
//            Date now = DateUtil.now();
//            if (entity.getId() == null) {
//                if (user != null) {
//                    entity.setCreateUser(user.getUserId());
//                    entity.setCreateDept(Func.firstLong(user.getDeptId()));
//                    entity.setUpdateUser(user.getUserId());
//                }
//
//                if (entity.getStatus() == null) {
//                    entity.setStatus(1);
//                }
//
//                entity.setCreateTime(now);
//            } else if (user != null) {
//                entity.setUpdateUser(user.getUserId());
//            }
//
//            entity.setUpdateTime(now);
//            entity.setIsDeleted(0);
//
//            Field field = ReflectUtil.getField(entity.getClass(), "tenantId");
//            if (ObjectUtil.isNotEmpty(field)) {
//                Method getTenantId = ClassUtil.getMethod(entity.getClass(), "getTenantId");
//                String tenantId = String.valueOf(getTenantId.invoke(entity));
//                if (ObjectUtil.isEmpty(tenantId)) {
//                    Method setTenantId = ClassUtil.getMethod(entity.getClass(), "setTenantId", String.class);
//                    setTenantId.invoke(entity);
//                }
//            }
//
//        } catch (Exception runtimeException) {
//            Console.log("调用mybatis-plus增强接口异常:{}", runtimeException.getMessage());
//        }
    }
}
