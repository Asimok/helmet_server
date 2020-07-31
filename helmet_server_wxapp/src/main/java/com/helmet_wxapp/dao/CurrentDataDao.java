package com.helmet_wxapp.dao;


import com.helmet_wxapp.data.CurrentData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataDao extends JpaRepository<CurrentData, String> {
}
