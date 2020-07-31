package com.helmet_wxapp.services;


import com.helmet_wxapp.dao.CurrentDataDao;
import com.helmet_wxapp.data.CurrentData;

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
