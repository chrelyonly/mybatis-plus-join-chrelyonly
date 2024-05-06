package cn.chrelyonly.maven.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author chrelyonly
 * 用于扩展mybatis-plus-join连表插件
 */
@Configuration
public class MybatisPlusConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);
    /**
     * 构造器初始化
     */
    MybatisPlusConfig(){
        log.info("mybatis-plus连表插件初始化");
    };
//    自行扩展,防止与其他插件冲突
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new MPJSqlInjector() {
//            @Override
//            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
//                List<AbstractMethod> list = super.getMethodList(mapperClass, tableInfo);
//                //添加你的方法
//                list.add(new InsertBatchSomeColumn());
//                return list;
//            }
//        };
//    }
}
