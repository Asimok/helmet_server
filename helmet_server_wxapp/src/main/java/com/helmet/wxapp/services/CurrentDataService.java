package com.helmet.wxapp.services;


import com.helmet.wxapp.dao.CurrentDataDao;
import com.helmet.wxapp.data.CurrentData;

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
