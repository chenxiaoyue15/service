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
@MapperScan(basePackages = "com.hzu.backstageservice.mapperss.**", sqlSessionFactoryRef = "turbssSqlSessionFactory")
public class TurbssDataSourceConfig {
    @Value("${spring.datasource.driver-class-name}")
    String driverClass;
    @Value("${spring.datasource.thirdary.url}")
    String url;
    @Value("${spring.datasource.thirdary.username}")
    String userName;
    @Value("${spring.datasource.thirdary.password}")
    String passWord;

    @Bean(name = "turbssDataSource")
    @ConfigurationProperties("spring.datasource.thirdary")
    public DataSource masterDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }

    @Bean(name = "turbssSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("turbssDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(configuration);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper3/*.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "turbssSqlSessionFactory")
    public SqlSessionTemplate sqlSessionFactoryTemplate(@Qualifier("turbssSqlSessionFactory")SqlSessionFactory sqlSessionFactory ) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
