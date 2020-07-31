package com.helmet_wxapp.controller;


import com.alibaba.fastjson.JSONObject;
import com.helmet_wxapp.data.LightHistory;
import com.helmet_wxapp.data.process_date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GetLight {

    @Autowired
    com.helmet_wxapp.dao.LightDao lightDao;


    @PostMapping("/GetLight")
    @ResponseBody
    public List<LightHistory> GetLight(@RequestBody String data) {
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        /*
         * 解析JSON字符串
         * helmet_id
         * */
        String temp_id = jsonObject.getString("helmet_id");
        String temp_begin = jsonObject.getString("begin");
        String temp_end = jsonObject.getString("end");
        System.out.println(temp_begin);

        List<LightHistory> optional = lightDao.findAll();
        List<LightHistory> temp = new ArrayList<>();
        for (int i = 0; i < optional.size(); i++) {
            if (optional.get(i).getHelmet_id().equals(temp_id)) {
                try {
                    if (process_date.check_dateToStamp(temp_begin, temp_end, String.valueOf(optional.get(i).getTime()))) {
                        LightHistory temp_temp = new LightHistory();
                        temp_temp.setHelmet_id(optional.get(i).getHelmet_id());
                        temp_temp.setLight(optional.get(i).getLight());
                        temp_temp.setTime(optional.get(i).getTime());
                        temp.add(temp_temp);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        return temp;
    }


}
