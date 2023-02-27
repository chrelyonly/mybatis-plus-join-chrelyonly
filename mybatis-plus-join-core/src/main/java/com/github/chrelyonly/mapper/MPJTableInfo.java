package com.github.chrelyonly.mapper;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 数据库表反射信息
 *
 * @author yulichang
 * @see TableInfo
 * @since 1.2.0
 */
@Data
@Accessors(chain = true)
public class MPJTableInfo {

    /**
     * mybatis-plus 表信息
     */
    private TableInfo tableInfo;

    /**
     * 是否包含 EntityMapping 或者 FieldMapping
     */
    private boolean hasMappingOrField;

    /**
     * 是否包含映射注解
     */
    private boolean hasMapping;

    /**
     * 是否包含映射注解
     */
    private boolean hasMappingField;

    /**
     * mapperClass
     */
    private Class<?> mapperClass;

    /**
     * 包含映射实体注解的字段列表
     */
    private List<com.github.chrelyonly.mapper.MPJTableFieldInfo> fieldList;
}
