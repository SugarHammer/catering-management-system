package com.zunke.shopmanager.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 16053
 */
@RestController
public class Log4jController {

    @Resource
    private Logger logger;

    @GetMapping("/testLog")
    public Map<String, Object> testLog() {
        System.out.println("logger----------------------------------------------------------" + logger);
        logger.trace("log running trace");
        logger.debug("log running debug");
        logger.info("log running info");
        logger.warn("log running warn");
        logger.error("log running error");
        logger.fatal("log running fatal");
        return null;
    }
}

