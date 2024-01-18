package com.example.Health.Record.Management.System.controller;


import com.example.Health.Record.Management.System.entity.HealthRecord;
import com.example.Health.Record.Management.System.payload.HealthRecordRequest;
import com.example.Health.Record.Management.System.services.HealthRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@Api(tags = "Health-Records")
@RequiredArgsConstructor
public class HealthRecordController {

    private final HealthRecordService healthRecordService;


    @PostMapping("/createrecord")
    @ApiOperation(value = "Create Record")
    public ResponseEntity<HealthRecordRequest> createRecord(@Valid @RequestBody HealthRecordRequest healthRecordRequest){
      HealthRecordRequest savedHealthRecord=  healthRecordService.createHealthRecord(healthRecordRequest);
               return new ResponseEntity<>(savedHealthRecord, HttpStatus.CREATED);
    }

    @PostMapping("/update-record")
    @ApiOperation(value = "Update Record")
    public ResponseEntity<HealthRecordRequest> updateRecord(@Valid @RequestBody HealthRecordRequest healthRecordRequest){
        HealthRecordRequest savedHealthRecord=  healthRecordService.updateHealthRecord(healthRecordRequest);
        return new ResponseEntity<>(savedHealthRecord, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find-record/{id}")
    @ApiOperation(value = "Get A Record By ID")
    public ResponseEntity<HealthRecord> findRecordById(@PathVariable ("id") String id){
        HealthRecord savedHealthRecord=  healthRecordService.findHealthRecordById(id);
        return new ResponseEntity<>(savedHealthRecord, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find-all-records")
    @ApiOperation(value = "Get All Records")
    public ResponseEntity<?> getAllRecords(){
        List<HealthRecord> savedHealthRecord=  healthRecordService.getAllRecord();
        return new ResponseEntity<>(savedHealthRecord, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete-record/{id}")
    @ApiOperation(value = "Delete Record By ID")
    public ResponseEntity<String> deleteRecordById(@PathVariable ("id") String id){
         String message = healthRecordService.deleteRecordById(id);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete-all-records/")
    @ApiOperation(value = "Delete All Records")
    public ResponseEntity<String> deleteAllRecord(){
        String message =  healthRecordService.deleteAllRecords();
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }


}
