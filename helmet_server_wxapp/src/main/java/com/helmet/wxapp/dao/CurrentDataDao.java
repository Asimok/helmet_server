package com.helmet.wxapp.dao;


import com.helmet.wxapp.data.CurrentData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataDao extends JpaRepository<CurrentData, String> {
}
