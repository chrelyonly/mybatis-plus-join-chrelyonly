package cn.chrelyonly.maven.util;

import cn.chrelyonly.maven.mapper.MyBaseMapper;
import cn.chrelyonly.maven.service.MyBaseService;
import cn.chrelyonly.maven.service.impl.MyBaseServiceImpl;

/**
 * 复杂service构造器
 * @author chrelyonly
 * 用于简化频繁创建service的繁琐操作
 * 传入 mapper 和 entity, 生成service
 * M extends MPJBaseMapper<T>, T
 * 用法与 BaseServiceImpl 一致
 * mapper接口类需要继承 MPJBaseMapper 而不是 BaseMapper
 * 或者自行通过service接口继承MPJBaseService
 */
public class MyBaseServiceImplUtil {
    /**
     * 构建mysql分表接口
     * @author chrelyonly
     * @param mClass mapper接口类
     * @return   service 自动装配
     * @param <M> mapper接口类  自动装配
     * @param <T> entity   自动装配
     * 食用教程: MyBaseService<返回接口的mapper> service = MyBaseServiceImplUtil.build(传入对应接口的mapper.class);
     */
    public static <M extends MyBaseMapper<T>, T> MyBaseService<T> build(Class<M> mClass){
        return new MyBaseServiceImpl<>(mClass);
    }

}
