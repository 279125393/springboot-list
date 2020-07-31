package com.ford;

import com.hazelcast.config.*;
import com.hazelcast.map.merge.PutIfAbsentMapMergePolicy;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@SpringBootApplication
@EnableAdminServer
public class App {
   /* @Bean
    public Config hazelcastConfig() {
        return new Config().setProperty("hazelcast.jmx", "true")
                .addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
                        .setEvictionPolicy(EvictionPolicy.NONE))
                .addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
                        .setMaxSize(1000));
    }*/
   @Bean
   public Config hazelcastConfig() {
       MapConfig mapConfig = new MapConfig("spring-boot-admin-event-store").setInMemoryFormat(InMemoryFormat.OBJECT)
               .setBackupCount(1)
               .setEvictionPolicy(EvictionPolicy.NONE)
               .setMergePolicyConfig(new MergePolicyConfig(
                       PutIfAbsentMapMergePolicy.class.getName(),
                       100
               ));
       Config config = new Config().setProperty("hazelcast.jmx", "true").addMapConfig(mapConfig);
       config.getNetworkConfig()
               .getJoin()
               .getMulticastConfig()
               .setEnabled(false);
       TcpIpConfig tcpIpConfig = config.getNetworkConfig()
               .getJoin()
               .getTcpIpConfig();
       tcpIpConfig.setEnabled(true);
       //localhost 改为 127.0.0.1
       tcpIpConfig.setMembers(Collections.singletonList("127.0.0.1"));
       return config;
   }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}