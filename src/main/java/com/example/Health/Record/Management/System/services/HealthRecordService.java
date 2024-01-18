package com.example.Health.Record.Management.System.services;

import com.example.Health.Record.Management.System.entity.HealthRecord;
import com.example.Health.Record.Management.System.payload.HealthRecordRequest;

import java.util.List;

public interface HealthRecordService {

    HealthRecordRequest createHealthRecord(HealthRecordRequest healthRecordRequest);
    HealthRecordRequest updateHealthRecord(HealthRecordRequest healthRecordRequest);
    HealthRecord findHealthRecordById(String id );

    List<HealthRecord> getAllRecord();

    String deleteAllRecords();
    String deleteRecordById(String id);



}
