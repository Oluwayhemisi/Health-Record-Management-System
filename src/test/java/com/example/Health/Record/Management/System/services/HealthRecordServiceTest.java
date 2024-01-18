package com.example.Health.Record.Management.System.services;

import com.example.Health.Record.Management.System.entity.HealthRecord;
import com.example.Health.Record.Management.System.entity.Role;
import com.example.Health.Record.Management.System.payload.HealthRecordRequest;
import com.example.Health.Record.Management.System.payload.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HealthRecordServiceTest {

    @Autowired
    private HealthRecordService healthRecordService;

    @Autowired
    private UserService userService;


    @Test
    @Transactional
    public void testCreateHealthRecord() {
        UserRequest userRequest = createValidUserRequest();

        HealthRecordRequest request = new HealthRecordRequest();
        request.setRecordId(1L);
        request.setUserEmail(userRequest.getEmail());
        HashMap<String, String> vitals = new HashMap<>();
        vitals.put("bloodPressure", "120/80");
        vitals.put("heartRate","75");
        request.setVitalSigns(vitals);

        List<String> medications = new ArrayList<>();
        medications.add("Aspirin");
        medications.add("Paracetamol");
        request.setMedications(medications);

        LocalDate appointmentDate = LocalDate.of(2024, 1, 25);
        request.setAppointmentDate(appointmentDate);

        HealthRecordRequest createdRecord = healthRecordService.createHealthRecord(request);


        assertThat(createdRecord.getAppointmentDate()).isEqualTo(request.getAppointmentDate());
        assertThat(createdRecord.getRecordId()).isEqualTo(request.getRecordId());

    }
    private HealthRecordRequest createRecordRequest(){
        UserRequest userRequest = createValidUserRequest();

        HealthRecordRequest request = new HealthRecordRequest();
        request.setRecordId(1L);
        request.setUserEmail(userRequest.getEmail());
        HashMap<String, String> vitals = new HashMap<>();
        vitals.put("bloodPressure", "120/80");
        vitals.put("heartRate","75");
        request.setVitalSigns(vitals);

        List<String> medications = new ArrayList<>();
        medications.add("Aspirin");
        medications.add("Paracetamol");
        request.setMedications(medications);

        LocalDate appointmentDate = LocalDate.of(2024, 1, 25);
        request.setAppointmentDate(appointmentDate);

        healthRecordService.createHealthRecord(request);
        return request;
    }
    private UserRequest createValidUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("yy@gmail.com");
        userRequest.setUserName("Addah");
        userRequest.setPassword("5555");
        userRequest.setRoles(Role.ROLE_PATIENT);
         userService.register(userRequest);
         return userRequest;
    }

    @Test
    public void testUpdateHealthRecord() {
        HealthRecordRequest savedRequest = createRecordRequest();
        List<String> medications = new ArrayList<>();
        medications.add("Omeprazole");
        medications.add("Metronidazole");

        savedRequest.setMedications(medications);
        HealthRecordRequest updatedHealthRecord = healthRecordService.updateHealthRecord(savedRequest);

        assertThat(updatedHealthRecord.getMedications()).isEqualTo(savedRequest.getMedications());

    }

    @Test
    public void testFindHealthRecordById() {
        HealthRecordRequest savedRequest = createRecordRequest();

        HealthRecord foundRecord = healthRecordService.findHealthRecordById(String.valueOf(savedRequest.getRecordId()));

        assertThat(foundRecord.getAppointmentDate()).isEqualTo(savedRequest.getAppointmentDate());
    }

    @Test
    public void testGetAllRecord() {
        createRecordRequest();

        List<HealthRecord> records = healthRecordService.getAllRecord();
        assertThat(records.size()).isEqualTo(1);
    }
    @Test
    public void testDeleteRecordById() {
        HealthRecordRequest request = createRecordRequest();
       String message = healthRecordService.deleteRecordById(request.getRecordId().toString());

       assertThat(message).isEqualTo("Record with the Id is successfully Deleted");
    }

    @Test
    public void testDeleteAllRecords() {
        createRecordRequest();
        String message = healthRecordService.deleteAllRecords();

        assertThat(message).isEqualTo("All Records Successfully Deleted");
    }



}
