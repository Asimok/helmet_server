package com.helmet.subscribe.subscribeservice;

import com.helmet.subscribe.common.Contants;
import com.helmet.subscribe.data.CurrentData;
import com.helmet.subscribe.data.GPSHistory;
import com.helmet.subscribe.data.LightHistory;
import com.helmet.subscribe.data.MpuHistory;
import com.helmet.subscribe.data.TemperatureHistory;
import com.helmet.subscribe.services.CurrentDataService;
import com.helmet.subscribe.services.GPSHistoryService;
import com.helmet.subscribe.services.LightHistoryService;
import com.helmet.subscribe.services.MpuHistoryService;
import com.helmet.subscribe.services.TemperatureHistoryService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

/**
 * 订阅者
 */
@Service
@Slf4j
public class SubscribeService {

    CurrentData currentData = new CurrentData();
    @Autowired
    private TemperatureHistoryService temperatureService;
    @Autowired
    private LightHistoryService lightHistoryService;
    @Autowired
    private GPSHistoryService gpsHistoryService;
    @Autowired
    private MpuHistoryService mpuHistoryService;
    @Autowired
    private CurrentDataService currentDataService;

    @Bean
    @ServiceActivator(inputChannel = Contants.MQTT_SUBSCRIBE_CHANNEL)
    public MessageHandler messageHandler() {
        MessageHandler messageHandler = message -> {
            //System.out.println("订阅者订阅消息头是：" + message.getHeaders().toString());
            System.out.println("订阅者订阅消息内容是：" + message.getPayload().toString());
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.fromObject(message.getPayload().toString());
                switch (jsonObject.getString("sensor")) {
                    case "temperature":
                        temperatureHandler(jsonObject);
                        break;
                    case "light":
                        lightHandler(jsonObject);
                        break;
                    case "gps":
                        gpsHandler(jsonObject);
                        break;
                    case "mpu6050":
                        mpuHandler(jsonObject);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return messageHandler;
    }


    private void mpuHandler(JSONObject jsonObject) {
        MpuHistory mpuHistory = new MpuHistory();
        int warning = jsonObject.getInt("warning");
        long time = System.currentTimeMillis();
        String helmet_id = jsonObject.getString("helmet_id");
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            mpuHistory.setHelmet_id(helmet_id);
            mpuHistory.setTime(time);
            mpuHistory.setWarning(warning);
            mpuHistoryService.saveMpu(mpuHistory);
            return null;
        });
        new Thread(futureTask).start();
        currentData.setHelmet_id(helmet_id);
        currentData.setWarning(warning);
        currentData.setTime(time);
        currentDataService.saveCurrentData(currentData);
    }

    private void gpsHandler(JSONObject jsonObject) {
        GPSHistory gpsHistory = new GPSHistory();
        String helmet_id = jsonObject.getString("helmet_id");
        long time = System.currentTimeMillis();
        String longitude = jsonObject.getString("longitude");
        String latitude = jsonObject.getString("latitude");
        String n_s = jsonObject.getString("N_S");
        String e_w = jsonObject.getString("E_W");
        String altitude = jsonObject.getString("altitude");
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            gpsHistory.setHelmet_id(helmet_id);
            gpsHistory.setTime(time);
            gpsHistory.setLongitude(longitude);
            gpsHistory.setLatitude(latitude);
            gpsHistory.setN_S(n_s);
            gpsHistory.setE_W(e_w);
            gpsHistory.setAltitude(altitude);
            gpsHistoryService.saveGPS(gpsHistory);
            return null;
        });
        new Thread(futureTask).start();
        currentData.setHelmet_id(helmet_id);
        currentData.setLongitude(longitude);
        currentData.setLatitude(latitude);
        currentData.setN_S(n_s);
        currentData.setE_W(e_w);
        currentData.setAltitude(altitude);
        currentData.setTime(time);
        currentDataService.saveCurrentData(currentData);
    }

    private void lightHandler(JSONObject jsonObject) {
        LightHistory lightHistory = new LightHistory();
        String helmet_id = jsonObject.getString("helmet_id");
        long time = System.currentTimeMillis();
        double light = jsonObject.getDouble("light");
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            lightHistory.setHelmet_id(helmet_id);
            lightHistory.setTime(time);
            lightHistory.setLight(light);
            lightHistoryService.saveLight(lightHistory);
            return null;
        });
        new Thread(futureTask).start();
        currentData.setHelmet_id(helmet_id);
        currentData.setTime(time);
        currentData.setLight(light);
        currentDataService.saveCurrentData(currentData);
    }

    private void temperatureHandler(JSONObject jsonObject) {
        TemperatureHistory temperatureHistory = new TemperatureHistory();
        String helmet_id = jsonObject.getString("helmet_id");
        long time = System.currentTimeMillis();
        double temperature = jsonObject.getDouble("temperature");
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            temperatureHistory.setHelmet_id(helmet_id);
            temperatureHistory.setTime(System.currentTimeMillis());
            temperatureHistory.setTemperature(temperature);
            temperatureService.saveTemperature(temperatureHistory);
            return null;
        });
        new Thread(futureTask).start();
        currentData.setHelmet_id(helmet_id);
        currentData.setTime(time);
        currentData.setTemperature(temperature);
        currentDataService.saveCurrentData(currentData);
    }
}
