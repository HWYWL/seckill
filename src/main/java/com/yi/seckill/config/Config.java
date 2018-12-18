package com.yi.seckill.config;

import org.springframework.context.annotation.Configuration;

/**
 * 配置文件
 * @author YI
 * @date 2018-10-19 10:16:51
 */
@Configuration
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.yi.seckill.dao")
public class Config {

}
