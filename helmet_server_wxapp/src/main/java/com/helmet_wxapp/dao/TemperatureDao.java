package com.helmet_wxapp.dao;


import com.helmet_wxapp.data.TemperatureHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureDao extends JpaRepository<TemperatureHistory, Long> {
}