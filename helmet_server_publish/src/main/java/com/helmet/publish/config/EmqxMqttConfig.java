package com.helmet.publish.config;

import com.helmet.publish.common.Contants;
import com.helmet.publish.properties.EmqxMqttProperties;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * emqx的配置工具类
 */
@Configuration
@Slf4j
@IntegrationComponentScan
public class EmqxMqttConfig {

    @Resource
    private EmqxMqttProperties emqxMqttProperties;


    /**
     * mqtt的连接
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        //设置相关的属性
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(emqxMqttProperties.getUsername());
        mqttConnectOptions.setPassword(emqxMqttProperties.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{emqxMqttProperties.getHostUrl()});
        //心跳
        mqttConnectOptions.setKeepAliveInterval(emqxMqttProperties.getKeepAlive());
        mqttConnectOptions.setMqttVersion(4);
        mqttConnectOptions.setConnectionTimeout(emqxMqttProperties.getTimeout());
        //清空曾经连接的客户端信息
        mqttConnectOptions.setCleanSession(false);
        //qos
        String playload = "设备断开连接";
        mqttConnectOptions.setWill("helmet_topic", playload.getBytes(), emqxMqttProperties.getQOs(), false);
        return mqttConnectOptions;
    }

    /**
     * paho factory ,把上面的mqtt的自定义连接放入factory工程中，便于连接需要
     */
    @Bean
    public MqttPahoClientFactory getMqttPahoClientFactory() {
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        defaultMqttPahoClientFactory.setConnectionOptions(getMqttConnectOptions());
        return defaultMqttPahoClientFactory;
    }

    /**
     * 开启连接通道
     */
    @Bean(name = Contants.MQTT_PUBLISH_CHANNEL)
    public MessageChannel getMqttPublishMessageChannel() {
        DirectChannel directChannel = new DirectChannel();
        return directChannel;

    }

    /**
     * 发布者，生产者
     */
    @Bean
    @ServiceActivator(inputChannel = Contants.MQTT_PUBLISH_CHANNEL)
    public MessageHandler getMqttMessageHandler() {
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(emqxMqttProperties.getClientId(), getMqttPahoClientFactory());
        mqttPahoMessageHandler.setAsync(true);
        mqttPahoMessageHandler.setDefaultQos(emqxMqttProperties.getQOs());
        mqttPahoMessageHandler.setDefaultTopic(emqxMqttProperties.getDefaultTopic());
        return mqttPahoMessageHandler;

    }


}
