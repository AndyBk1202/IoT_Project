package com.andy.iot.repository;

import com.andy.iot.model.SensorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRecordRepository extends JpaRepository<SensorRecord, Integer> {
}
