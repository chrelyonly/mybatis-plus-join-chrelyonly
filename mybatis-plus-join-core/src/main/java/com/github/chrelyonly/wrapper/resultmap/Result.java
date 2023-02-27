package com.github.chrelyonly.wrapper.resultmap;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.chrelyonly.toolkit.LambdaUtils;
import com.github.chrelyonly.toolkit.support.ColumnCache;
import com.github.chrelyonly.wrapper.segments.SelectCache;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;
import java.util.Objects;

/**
 * result 标签
 *
 * @author yulichang
 * @since 1.3.0
 */
@Getter
@Setter(AccessLevel.PACKAGE)
public class Result {

    private boolean isId;

    private String index;

    private SelectCache selectNormal;

    private String property;

    private Class<?> javaType;

    private JdbcType jdbcType;

    public Result() {
    }


    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder<E, T> {

        private final Result result;

        public Builder(boolean isId) {
            this.result = new Result();
            result.isId = isId;
        }

        public Builder<E, T> property(SFunction<T, ?> property) {
            result.property = LambdaUtils.getName(property);
            return this;
        }

        public Builder<E, T> column(SFunction<E, ?> column) {
            Class<E> entityClass = LambdaUtils.getEntityClass(column);
            Map<String, SelectCache> normalMap = ColumnCache.getMapField(entityClass);
            String name = LambdaUtils.getName(column);
            SelectCache normal = normalMap.get(name);
            result.selectNormal = normal;
            if (StringUtils.isBlank(result.property)) {
                result.property = normal.getColumProperty();
            }
            if (Objects.isNull(result.javaType)) {
                result.javaType = normal.getColumnType();
            }
            if (Objects.isNull(result.jdbcType)) {
                result.jdbcType = Objects.isNull(normal.getTableFieldInfo()) ? null : normal.getTableFieldInfo().getJdbcType();
            }
            return this;
        }

        public Builder<E, T> javaType(Class<?> javaType) {
            result.javaType = javaType;
            return this;
        }

        public Builder<E, T> jdbcType(JdbcType jdbcType) {
            result.jdbcType = jdbcType;
            return this;
        }

        public Result build() {
            return result;
        }

    }
}
