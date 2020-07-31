package com.helmet.subscribe.services;

import com.helmet.subscribe.dao.GPSDao;
import com.helmet.subscribe.data.GPSHistory;

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
