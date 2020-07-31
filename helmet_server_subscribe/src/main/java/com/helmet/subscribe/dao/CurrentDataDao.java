package com.helmet.subscribe.dao;

import com.helmet.subscribe.data.CurrentData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataDao extends JpaRepository<CurrentData, String> {
}
