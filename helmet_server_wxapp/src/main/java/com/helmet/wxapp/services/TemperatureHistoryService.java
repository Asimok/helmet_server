package com.helmet.wxapp.services;


import com.helmet.wxapp.data.TemperatureHistory;
import com.helmet.wxapp.dao.TemperatureDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureHistoryService {
    @Autowired
    private TemperatureDao dataDao;

    public TemperatureHistoryService(TemperatureDao dataDao) {
        this.dataDao = dataDao;
    }

    public void saveTemperature(TemperatureHistory bookCollect) {
        dataDao.save(bookCollect);
    }

}
