package com.helmet.wxapp.services;


import com.helmet.wxapp.dao.GPSDao;
import com.helmet.wxapp.data.GPSHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GPSHistoryService {
    @Autowired
    private GPSDao gpsDao;

    public GPSHistoryService(GPSDao gpsDao) {
        this.gpsDao = gpsDao;
    }

    public void saveGPS(GPSHistory gpsHistory) {
        gpsDao.save(gpsHistory);
    }
}
