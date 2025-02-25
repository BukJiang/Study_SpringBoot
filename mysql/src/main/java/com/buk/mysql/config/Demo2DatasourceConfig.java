package com.buk.mysql.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * TODO: 多数据源(Hikari + MybatisPlus)配置
 * TODO: {@link ConfigurationProperties} 读取application.properties中的配置参数映射成对象
 *
 * @author BuK
 * @since 2020/08/21
 */
//@Configuration
//@MapperScan(
//        basePackages = "com.buk.example.mapper.demo2",
//        sqlSessionFactoryRef = "demo2SqlSessionFactory"
//)
public class Demo2DatasourceConfig {

//    ======= Datasource 不同的连接池，数据源Bean配置不一样 =======

//    ======= SqlSessionFactory 不同的持久层框架，SqlSessionFactory的Bean配置不一样 =======

    /**
     * HikariConfig
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.demo2")
    public HikariConfig demo2HikariConfig() {
        return new HikariConfig();
    }

    /**
     * 数据源
     *
     * @return
     */
    @Bean
    public DataSource demo2DataSource(@Qualifier("demo2HikariConfig") HikariConfig demo2HikariConfig) {
        return new HikariDataSource(demo2HikariConfig);
    }

    /**
     * 配置事务管理
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager demo2TransactionManager(@Qualifier("demo2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory demo2SqlSessionFactory(@Qualifier("demo2DataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/**/*.xml")
        );
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate demo2SqlSessionTemplate(@Qualifier("demo2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
