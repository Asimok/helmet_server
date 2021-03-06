package com.helmet.publish.service;


import com.helmet.publish.common.Contants;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@MessagingGateway(defaultRequestChannel = Contants.MQTT_PUBLISH_CHANNEL)
@Component
public interface PublishService {

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);

    void sendToMqtt(String data);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String data);

}
