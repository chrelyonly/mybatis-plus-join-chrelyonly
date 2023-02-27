package com.github.chrelyonly.wrapper.segments;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.chrelyonly.wrapper.enums.BaseFuncEnum;
import org.apache.ibatis.type.TypeHandler;

/**
 * 查询列
 *
 * @author yulichang
 * @since 1.3.10
 */
public interface Select {

    Class<?> getClazz();

    Integer getIndex();

    boolean isHasTableAlias();

    String getTableAlias();

    boolean isPk();

    String getColumn();

    Class<?> getColumnType();

    String getTagColumn();

    String getColumProperty();

    boolean hasTypeHandle();

    TypeHandler<?> getTypeHandle();

    boolean isHasAlias();

    String getAlias();

    TableFieldInfo getTableFieldInfo();

    boolean isFunc();

    SFunction<?, ?>[] getArgs();

    BaseFuncEnum getFunc();

    boolean isLabel();

    boolean isStr();
}
