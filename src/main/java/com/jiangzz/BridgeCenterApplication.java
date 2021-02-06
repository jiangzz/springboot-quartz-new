package com.jiangzz;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import sun.lwawt.macosx.CSystemTray;

import javax.sql.DataSource;
import java.io.IOException;
@SpringBootApplication
public class BridgeCenterApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(BridgeCenterApplication.class,args);
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
    DataSource quartzDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
}
