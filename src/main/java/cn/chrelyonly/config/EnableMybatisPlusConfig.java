package cn.chrelyonly.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chrelyonly
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyAutoConfig.class)
public @interface EnableMybatisPlusConfig {
}
