package com.helmet_wxapp.services;



import com.helmet_wxapp.dao.TemperatureDao;
import com.helmet_wxapp.data.TemperatureHistory;

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
