package cn.chrelyonly.maven.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chrelyonly
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyAutoConfig.class)
public @interface MyEnableMybatisPlusJoinConfig {
}
