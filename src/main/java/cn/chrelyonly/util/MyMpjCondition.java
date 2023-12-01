package cn.chrelyonly.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 用于构造 对应的QueryWrapper查询器与分页对象
 * @author chrelyonly
 */
public class MyMpjCondition {
    /**
     * 传入分页的参数,返回分页对象
     * @param current 当前页
     * @param size 每页大小
     */
    public static <T> IPage<T> getPage(Integer current, Integer size) {
        return new Page<>(current, size);
    }

    /**
     * 传入实体返回对应的MPJQueryWrapper
     * @param entity 实体
     */
    public static <T> MPJLambdaWrapper<T> getQueryWrapper(T entity) {
        return new MPJLambdaWrapper<>(entity);
    }
    /**
     * 传入实体返回对应的MPJLamQueryWrapper
     * 返回自定义构造的参数,默认like查询
     * @param query 实体转map的参数
     * @param clazz 实体.class
     */
    public static <T> MPJLambdaWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        MPJLambdaWrapper<T> qw = new MPJLambdaWrapper<>();
        qw.setEntity(BeanUtils.instantiateClass(clazz));
//        动态构造查询条件
        SqlKeyword.buildCondition(query, qw);
        return qw;
    }

}
