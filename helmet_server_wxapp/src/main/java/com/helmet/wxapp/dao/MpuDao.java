package com.helmet.wxapp.dao;


import com.helmet.wxapp.data.MpuHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpuDao extends JpaRepository<MpuHistory, Long> {
}
