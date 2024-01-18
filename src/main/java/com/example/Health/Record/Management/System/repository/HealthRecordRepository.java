package com.example.Health.Record.Management.System.repository;

import com.example.Health.Record.Management.System.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
//    Optional<HealthRecord> findHealthRecordByUserId(Long id);
    Optional<HealthRecord> findHealthRecordByRecordId(Long id);


}
