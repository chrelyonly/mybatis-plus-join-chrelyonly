package cn.chrelyonly.service.impl;


import cn.chrelyonly.mapper.MyBaseMapper;
import cn.chrelyonly.service.MyBaseService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.toolkit.SpringContentUtils;

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
public class MyBaseServiceImpl<M extends MyBaseMapper<T>, T> extends MPJBaseServiceImpl<M, T> implements MyBaseService<T> {
    /**`
     * 核心方法
      * @param mClass mapper接口类
     */
    public MyBaseServiceImpl(Class<M> mClass){
        this.baseMapper = SpringContentUtils.getBean(mClass);
    }
}
