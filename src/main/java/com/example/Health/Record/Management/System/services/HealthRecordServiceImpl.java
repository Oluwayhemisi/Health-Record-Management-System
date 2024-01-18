package com.example.Health.Record.Management.System.services;

import com.example.Health.Record.Management.System.entity.HealthRecord;
import com.example.Health.Record.Management.System.entity.User;
import com.example.Health.Record.Management.System.exceptions.HealthRecordException;
import com.example.Health.Record.Management.System.payload.HealthRecordRequest;
import com.example.Health.Record.Management.System.repository.HealthRecordRepository;
import com.example.Health.Record.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class HealthRecordServiceImpl implements HealthRecordService{
    private final HealthRecordRepository healthRecordRepository;

    private final UserRepository userRepository;

    private final ModelMapper mapper;


    @Override
    public HealthRecordRequest createHealthRecord(HealthRecordRequest healthRecordRequest) {
       HealthRecord newHealthRecord = new HealthRecord();
       newHealthRecord.setAppointmentDate(healthRecordRequest.getAppointmentDate());

       newHealthRecord.setVitalSigns(healthRecordRequest.getVitalSigns());
       newHealthRecord.setMedications(healthRecordRequest.getMedications());

      User user= userRepository.findByEmail(healthRecordRequest.getUserEmail()).orElseThrow(()-> new HealthRecordException("User not found", HttpStatus.NOT_FOUND) );
        newHealthRecord.setUser(user);

       healthRecordRepository.save(newHealthRecord);

        return mapper.map(newHealthRecord,HealthRecordRequest.class);
    }

    @Override
    public HealthRecordRequest updateHealthRecord(HealthRecordRequest healthRecordRequest) {
       HealthRecord updateRecord = healthRecordRepository.findHealthRecordByRecordId(healthRecordRequest.getRecordId()).orElseThrow(()-> new HealthRecordException("Record not found",HttpStatus.NOT_FOUND));
       HealthRecord savedHealthRecord = mapper.map(healthRecordRequest,HealthRecord.class);

       updateRecord.setMedications(healthRecordRequest.getMedications());
       updateRecord.setVitalSigns(healthRecordRequest.getVitalSigns());
       updateRecord.setAppointmentDate(healthRecordRequest.getAppointmentDate());
       healthRecordRepository.save(updateRecord);

       return mapper.map(savedHealthRecord,HealthRecordRequest.class);




    }

    @Override
    public HealthRecord findHealthRecordById(String id) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByRecordId(Long.valueOf(id)).orElseThrow(()-> new HealthRecordException("Record not found",HttpStatus.NOT_FOUND));

        return healthRecord;
    }

    @Override
    public List<HealthRecord> getAllRecord() {
        return healthRecordRepository.findAll();
    }

    @Override
    public String deleteAllRecords() {
        healthRecordRepository.deleteAll();
        return "All Records Successfully Deleted";
    }

    @Override
    public String deleteRecordById(String id) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByRecordId(Long.valueOf(id)).orElseThrow(()-> new HealthRecordException("Record not found",HttpStatus.NOT_FOUND));
        healthRecordRepository.delete(healthRecord);
        return "Record with the Id is successfully Deleted";
    }
}
