package com.helmet.wxapp.controller;


import com.alibaba.fastjson.JSONObject;
import com.helmet.wxapp.dao.TemperatureDao;
import com.helmet.wxapp.data.TemperatureHistory;
import com.helmet.wxapp.data.process_date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GetTemerature {

    @Autowired
    TemperatureDao temperatureDao;


    @PostMapping("/GetTemerature")
    @ResponseBody
    public List<TemperatureHistory> GetTemerature(@RequestBody String data) {
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        /*
         * 解析JSON字符串
         * helmet_id
         * */
        String temp_id = jsonObject.getString("helmet_id");
        String temp_begin = jsonObject.getString("begin");
        String temp_end = jsonObject.getString("end");
        System.out.println(temp_id);

        List<TemperatureHistory> optional = temperatureDao.findAll();
        List<TemperatureHistory> temp = new ArrayList<>();
//        for (int i = 0; i < optional.size(); i++) {
//            if (optional.get(i).getHelmet_id().toString().equals(temp_id)) {
//                try {
//                    if (process_date.check_dateToStamp(temp_begin, temp_end, String.valueOf(optional.get(i).getTime()))) {
//                        TemperatureHistory temp_temp = new TemperatureHistory();
//                        temp_temp.setHelmet_id(optional.get(i).getHelmet_id());
//                        temp_temp.setTemperature(optional.get(i).getTemperature());
//                        temp_temp.setTime(optional.get(i).getTime());
//                        temp.add(temp_temp);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        temp = optional.stream().filter(id-> {
            try {
                return id.getHelmet_id().equals(temp_id)&&process_date.
                        check_dateToStamp(temp_begin, temp_end, String.valueOf(id.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        return temp;
    }


}
