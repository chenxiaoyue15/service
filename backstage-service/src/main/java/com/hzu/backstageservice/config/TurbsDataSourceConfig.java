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


import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.hzu.backstageservice.mappers.**", sqlSessionFactoryRef = "turbsSqlSessionFactory")
public class TurbsDataSourceConfig {
    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.secondary.url}")
    String url;
    @Value("${spring.datasource.secondary.username}")
    String userName;
    @Value("${spring.datasource.secondary.password}")
    String passWord;

    @Bean(name = "turbsDataSource")
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource masterDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }

    @Bean(name = "turbsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("turbsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(configuration);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper2/*.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "turbsSqlSessionFactory")
    public SqlSessionTemplate sqlSessionFactoryTemplate(@Qualifier("turbsSqlSessionFactory")SqlSessionFactory sqlSessionFactory ) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
