package com.helmet_wxapp.services;



import com.helmet_wxapp.dao.MpuDao;
import com.helmet_wxapp.data.MpuHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MpuHistoryService {
    @Autowired
    private MpuDao mpuDao;
    public MpuHistoryService(MpuDao mpuDao) {
        this.mpuDao = mpuDao;
    }

    public void saveMpu(MpuHistory mpuHistory) {
        mpuDao.save(mpuHistory);
    }
}
