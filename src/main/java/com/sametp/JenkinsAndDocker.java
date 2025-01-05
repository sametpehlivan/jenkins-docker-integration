package com.sametp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinsAndDocker {
    static Logger logger = LoggerFactory.getLogger(JenkinsAndDocker.class);
    public static void main(String[] args) {
        SpringApplication.run(JenkinsAndDocker.class,args);
        logger.info("*********** APPLICATION STARTED ********");
    }
    @GetMapping
    @ResponseBody
    public String test(){
        String message = "*********** APPLICATION IS UP ********";
        logger.info(message);
        return message;
    }
    @GetMapping("/v2")
    @ResponseBody
    public String projectUpdate(){
        String message = "*********** UPDATED APPLICATION IS UP ********";
        logger.info(message);
        return message;
    }
}
