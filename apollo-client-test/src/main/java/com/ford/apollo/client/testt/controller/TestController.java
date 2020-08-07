/*
 * The MIT License (MIT)
 * Copyright © 2020 <reach>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the “Software”), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ford.apollo.client.testt.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.ford.apollo.client.testt.configuration.RedisConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author hutao
 * @Date 2020/8/5
 * @Version V1.0
 **/
@RestController
public class TestController {
    @Autowired
    private RedisConfigurationProperties redisConfigurationProperties;
    @Value("${uri:default}")
    private String uri;
    @Value("${redis.port:default}")
    private String port;

    @GetMapping("/test")
    public String test(){
        return uri;
    }
    @GetMapping("/bean")
    public String bean(){
        return redisConfigurationProperties.getPort();
    }
    @GetMapping("/redis")
    public String tret(){
        return port;
    }

    @ApolloConfigChangeListener("redis.config")
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("redis.port")) {
            redisConfigurationProperties.setPort(changeEvent.getChange("redis.port").getNewValue());
        }
    }
  /*  @ApolloConfig
    private Config config; //inject config for namespace application
    @ApolloConfig("application")
    private Config anotherConfig; //inject config for namespace application
    @ApolloConfig("FX.apollo")
    private Config yetAnotherConfig; //inject config for namespace FX.apollo
    @ApolloConfig("application.yml")
    private Config ymlConfig; //inject config for namespace application.yml

    *//**
     * ApolloJsonValue annotated on fields example, the default value is specified as empty list - []
     * <br />
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     *//*
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<JsonBean> anotherJsonBeans;

    @Value("${batch:100}")
    private int batch;

    //config change listener for namespace application
    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("batch")) {
            batch = config.getIntProperty("batch", 100);
        }
    }

    //config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        //do something
    }

    //config change listener for namespaces application, FX.apollo and application.yml
    @ApolloConfigChangeListener({"application", "FX.apollo", "application.yml"})
    private void yetAnotherOnChange(ConfigChangeEvent changeEvent) {
        //do something
    }

    //example of getting config from Apollo directly
    //this will always return the latest value of timeout
    public int getTimeout() {
        return config.getIntProperty("timeout", 200);
    }

    //example of getting config from injected value
    //the program needs to update the injected value when batch is changed in Apollo using @ApolloConfigChangeListener shown above
    public int getBatch() {
        return this.batch;
    }

    private static class JsonBean{
        private String someString;
        private int someInt;
    }*/
}