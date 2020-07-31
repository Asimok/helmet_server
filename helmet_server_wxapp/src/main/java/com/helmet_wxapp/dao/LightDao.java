package com.helmet_wxapp.dao;



import com.helmet_wxapp.data.LightHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightDao extends JpaRepository<LightHistory, Long> {
}
