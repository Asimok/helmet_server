package com.helmet.subscribe.config;

import com.helmet.subscribe.common.Contants;
import com.helmet.subscribe.properties.EmqxMqttProperties;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * emqx的配置工具类
 */
@Configuration
@IntegrationComponentScan
@Slf4j
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
    @Bean(name = Contants.MQTT_SUBSCRIBE_CHANNEL)
    public MessageChannel getMqttSubscribeMessageChannel() {
        DirectChannel directChannel = new DirectChannel();
        return directChannel;

    }

    /**
     * 监听topic,订阅者，消费者
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(
                emqxMqttProperties.getClientId() + "_helmet", getMqttPahoClientFactory(), emqxMqttProperties.getDefaultTopic().split(","));
        mqttPahoMessageDrivenChannelAdapter.setDisconnectCompletionTimeout(emqxMqttProperties.getTimeout());
        mqttPahoMessageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        mqttPahoMessageDrivenChannelAdapter.setQos(emqxMqttProperties.getQOs());
        mqttPahoMessageDrivenChannelAdapter.setOutputChannel(getMqttSubscribeMessageChannel());
        return mqttPahoMessageDrivenChannelAdapter;

    }


}
