package com.spring.demo;

import com.ctrip.framework.apollo.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/20 11:37 AM
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
public class ApolloConfigResource {

    @GetMapping(path = "/config/{key}")
    public String getConfigForKey(@PathVariable("key") String key) {
        return ConfigService.getAppConfig().getProperty(key, "undefined");
    }

}
