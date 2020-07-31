package com.helmet.subscribe.dao;

import com.helmet.subscribe.data.LightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightDao extends JpaRepository<LightHistory, Long> {
}
