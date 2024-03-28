package com.zunke.shopmanager.config;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 16053
 */
@Configuration
public class Log4jConfig {
    /*
    public final static Logger logger = LogManager.getLogger();
    不依赖spring注入管理，则手动创建logger对象
     */

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger();
    }
}

