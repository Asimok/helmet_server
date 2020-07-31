package com.helmet_wxapp.controller;


import com.alibaba.fastjson.JSONObject;
import com.helmet_wxapp.data.GPSHistory;
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
public class GetGPS {

    @Autowired
    com.helmet_wxapp.dao.GPSDao gpsDao;


    @PostMapping("/GetGPS")
    @ResponseBody
    public List<GPSHistory> GetGPS(@RequestBody String data) {
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        /*
         * 解析JSON字符串
         * helmet_id
         * */
        String temp_id = jsonObject.getString("helmet_id");
        System.out.println(temp_id);
        String temp_begin = jsonObject.getString("begin");
        String temp_end = jsonObject.getString("end");

        List<GPSHistory> optional = gpsDao.findAll();
        List<GPSHistory> temp =new ArrayList<>();
       for (int i=0;i<optional.size();i++)
       {
           if(optional.get(i).getHelmet_id().toString().equals(temp_id))
           {
               try {
                   if(process_date.check_dateToStamp(temp_begin,temp_end,String.valueOf(optional.get(i).getTime())))
                   {
                       GPSHistory temp_temp = new GPSHistory();
                       temp_temp.setHelmet_id(optional.get(i).getHelmet_id());
                       temp_temp.setAltitude(optional.get(i).getAltitude());
                       temp_temp.setE_W(optional.get(i).getE_W());
                       temp_temp.setLatitude(optional.get(i).getLatitude());
                       temp_temp.setLongitude(optional.get(i).getLongitude());
                       temp_temp.setN_S(optional.get(i).getN_S());
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
