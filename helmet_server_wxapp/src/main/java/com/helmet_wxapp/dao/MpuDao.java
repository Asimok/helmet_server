package com.helmet_wxapp.dao;


import com.helmet_wxapp.data.MpuHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpuDao extends JpaRepository<MpuHistory, Long> {
}
