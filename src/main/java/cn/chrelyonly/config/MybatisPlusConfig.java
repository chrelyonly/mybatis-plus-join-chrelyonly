package cn.chrelyonly.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.github.yulichang.injector.MPJSqlInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author chrelyonly
 * 用于扩展mybatis-plus-join连表插件
 */
@Configuration
@ComponentScan("cn.chrelyonly")
public class MybatisPlusConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);
    /**
     * 构造器初始化
     */
    MybatisPlusConfig(){
        log.info("mybatis-plus连表插件初始化");
    };
    @Bean
    public ISqlInjector sqlInjector() {
        return new MPJSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> list = super.getMethodList(mapperClass, tableInfo);
                //添加你的方法
                list.add(new InsertBatchSomeColumn());
                return list;
            }
        };
    }
}
