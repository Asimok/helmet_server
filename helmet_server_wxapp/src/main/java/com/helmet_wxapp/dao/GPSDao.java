package com.helmet_wxapp.dao;


import com.helmet_wxapp.data.GPSHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSDao extends JpaRepository<GPSHistory, Long> {
}
