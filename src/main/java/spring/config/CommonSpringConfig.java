package spring.config;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.DependencyInject;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/13 21:38
 * @Description:测试spring配置注解@Configuration
 * 该类没有在bean.xml文件中存在
 */

@Configuration
@ComponentScan("spring.*")
@Import({MysqlConfig.class})
public class CommonSpringConfig {
    
    /** 
     * @description: 有些类在第三方jar包中，没法给他们加上@Service注解，也就不会
     * 存在spring的map里，通过@Bean注解可以创建bean对象
     * @param: 
     * @return: org.apache.commons.dbutils.QueryRunner
     * @author shenyafeng
     * @date: 2020/12/13 21:45
     */ 
    @Bean("queryRunner")
    QueryRunner getQueryRunner(){
        return new QueryRunner();
    }

    @Bean("dependencyInject")
    DependencyInject getDependencyInject(){
        return new DependencyInject();
    }
}
