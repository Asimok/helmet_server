package com.helmet.subscribe.dao;

import com.helmet.subscribe.data.MpuHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpuDao extends JpaRepository<MpuHistory, Long> {
}
