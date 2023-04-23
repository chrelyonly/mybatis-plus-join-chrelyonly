package cn.chrelyonly.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;

import java.util.Map;

/**
 * @author chrelyonly
 * 复杂sql转换warpper器
 */
public class MyCondition {
    /**
     * 获取分页对象
     * @param query 分页参数
     * @return 分页对象
     */
    public static <T> IPage<T> getPage(Query query) {
        Page<T> page = new Page<>(Func.toInt(query.getCurrent(), 1), Func.toInt(query.getSize(), 10));
        String[] ascArr = Func.toStrArray(query.getAscs());
        String[] descArr = ascArr;
        int var4 = ascArr.length;

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            String asc = descArr[var5];
            page.addOrder(OrderItem.asc(StringUtil.cleanIdentifier(asc)));
        }

        descArr = Func.toStrArray(query.getDescs());
        String[] var8 = descArr;
        var5 = descArr.length;

        for(int var9 = 0; var9 < var5; ++var9) {
            String desc = var8[var9];
            page.addOrder(OrderItem.desc(StringUtil.cleanIdentifier(desc)));
        }

        return page;
    }

    /**
     * 获取QueryWrapperJoin
     * @param entity 实体对象
     * @param <T>    泛型
     * @return MPJLambdaWrapper
     */
    public static <T> MPJLambdaWrapper<T> getQueryWrapperJoin(T entity) {
        return MPJWrappers.lambdaJoin(entity);
    }

    /**
     * 如果传入的是Map，那么就会自动转换为QueryWrapperJoin并且会动态生成
     * 默认返回模糊查询字段
     */
    public static <T> MPJLambdaWrapper<T> getQueryWrapperJoin(Map<String, Object> query, Class<T> clazz) {
        Kv exclude = Kv.create().set("Blade-Auth", "Blade-Auth").set("current", "current").set("size", "size").set("ascs", "ascs").set("descs", "descs");
        return getQueryWrapperJoin(query, exclude, clazz);
    }

    /**
     * 如果传入的是Map，那么就会自动转换为QueryWrapperJoin并且会动态生成
     * 默认返回模糊查询字段
     */
    public static <T> MPJLambdaWrapper<T> getQueryWrapperJoin(Map<String, Object> query, Map<String, Object> exclude, Class<T> clazz) {
        exclude.forEach((k, v) -> {
            query.remove(k);
        });
        MPJLambdaWrapper<T> qw = MPJWrappers.lambdaJoin();
        qw.setEntity(BeanUtil.newInstance(clazz));
        MySqlKeyword.buildCondition(query, qw);
        return qw;
    }
}
