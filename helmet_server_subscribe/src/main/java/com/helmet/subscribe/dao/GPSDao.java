package com.helmet.subscribe.dao;

import com.helmet.subscribe.data.GPSHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSDao extends JpaRepository<GPSHistory, Long> {
}
