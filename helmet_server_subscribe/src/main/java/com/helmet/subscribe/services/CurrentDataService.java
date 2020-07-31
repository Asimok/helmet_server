package com.helmet.subscribe.services;

import com.helmet.subscribe.dao.CurrentDataDao;
import com.helmet.subscribe.data.CurrentData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentDataService {
    @Autowired
    private CurrentDataDao currentDataDao;

    public CurrentDataService(CurrentDataDao currentDataDao) {
        this.currentDataDao = currentDataDao;
    }

    public void saveCurrentData(CurrentData currentData) {
        currentDataDao.save(currentData);
    }
}
