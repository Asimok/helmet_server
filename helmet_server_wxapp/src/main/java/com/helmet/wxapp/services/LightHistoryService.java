package com.helmet.wxapp.services;


import com.helmet.wxapp.dao.LightDao;
import com.helmet.wxapp.data.LightHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LightHistoryService {
    @Autowired
    private LightDao lightDao;

    public LightHistoryService(LightDao lightDao) {
        this.lightDao = lightDao;
    }

    public void saveLight(LightHistory lightHistory) {
        lightDao.save(lightHistory);
    }
}
