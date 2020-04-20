package com.hzu.backstageservice.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.hzu.backstageservice.mapper.**", sqlSessionFactoryRef = "turbSqlSessionFactory")

public class TurbDataSourceConfig {
    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.primary.url}")
    String url;
    @Value("${spring.datasource.primary.username}")
    String userName;
    @Value("${spring.datasource.primary.password}")
    String passWord;

    @Primary
    @Bean(name = "turbDataSource")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }

//    @Bean
//    @ConfigurationProperties(prefix = "mybatis.configuration")
//    public Configuration configuration() {
//
//        return new Configuration();
//    }

    @Bean(name = "turbSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("turbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(configuration);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));

        return sessionFactoryBean.getObject();
    }
    @Bean(name = "turbSqlSessionFactory")
    public SqlSessionTemplate sqlSessionFactoryTemplate(@Qualifier("turbSqlSessionFactory")SqlSessionFactory sqlSessionFactory ) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
