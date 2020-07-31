package com.helmet.wxapp.dao;


import com.helmet.wxapp.data.LightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightDao extends JpaRepository<LightHistory, Long> {
}
