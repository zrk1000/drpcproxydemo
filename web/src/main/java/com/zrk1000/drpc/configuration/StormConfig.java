package com.zrk1000.drpc.configuration;

import com.zrk1000.drpc.annotation.ServiceScan;
import com.zrk1000.drpc.config.ExtendProperties;
import com.zrk1000.drpc.rpc.RpcHandle;
import com.zrk1000.drpc.rpc.drpc.StormLocalDrpcHandle;
import com.zrk1000.drpc.rpc.drpc.StormRemoteDrpcHandle;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.storm.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by rongkang on 2017-04-20.
 */
@Profile("local")
//@Profile("remote")
@Configuration
@ServiceScan(basePackages = "com.zrk1000.drpc.serviceimpl",rpcHandleBeanRef="stormDrpcHandle")
public class StormConfig {

    @Profile("local")
    @Bean("stormDrpcHandle")

    public RpcHandle getStormLocalRpcHandle(){
        StormLocalDrpcHandle drpcHandle = new StormLocalDrpcHandle();
        return  drpcHandle;
    }

    @Profile("remote")
    @Bean("stormDrpcHandle")
    public RpcHandle getStormRemoteRpcHandle(){
        Config config = new Config();
        ExtendProperties conf = new ExtendProperties();
        try {
            conf.load( StormConfig.class.getResourceAsStream("classpath*:drpcproxy-consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化 StormRemoteDrpcHandle 失败 ,读取配置文件错误");
        }
        config.putAll(conf.getSubProperty("stormconf.",true));
        String stormHost = conf.getProperty("strom.host");
        Integer stormPort = conf.getIntProperty("strom.port");
        Integer stormTimeout = conf.getIntProperty("strom.timeout");
        Map<String, Set<String>> subPropertyValueToSet = conf.getSubPropertyValueToSet("drpcspout.", true);

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(conf.getIntProperty("drpc.maxTotal"));
        poolConfig.setMaxIdle(conf.getIntProperty("drpc.maxIdle"));
        poolConfig.setMinIdle(conf.getIntProperty("drpc.minIdle"));

        StormRemoteDrpcHandle drpcHandle = new StormRemoteDrpcHandle(config,stormHost,stormPort,stormTimeout,poolConfig,subPropertyValueToSet);
        return  drpcHandle;

    }

}
