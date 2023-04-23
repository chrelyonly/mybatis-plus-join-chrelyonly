package cn.chrelyonly.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chrelyonly
 * 插件入口
 */
@Target(ElementType.TYPE)
@Import(MybatisPlusConfig.class)
public @interface EnableMybatisPlusConfig {
}
