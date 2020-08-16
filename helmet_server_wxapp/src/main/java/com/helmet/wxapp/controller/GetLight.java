package com.helmet.wxapp.controller;


import com.alibaba.fastjson.JSONObject;
import com.helmet.wxapp.dao.LightDao;
import com.helmet.wxapp.data.LightHistory;
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
public class GetLight {

    @Autowired
    LightDao lightDao;


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
