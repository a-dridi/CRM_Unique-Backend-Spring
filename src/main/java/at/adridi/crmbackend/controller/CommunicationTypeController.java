/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.crmbackend.controller;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.CommunicationType;
import at.adridi.crmbackend.model.Customer;
import at.adridi.crmbackend.service.CommunicationTypeService;
import at.adridi.crmbackend.util.ApiUrls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * All CommunicationTypes that are used in a CommunicationMessage
 *
 * @author A.Dridi
 */
@RestController
@NoArgsConstructor
public class CommunicationTypeController {

    @Autowired
    private CommunicationTypeService communicationTypeService;

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONTYPE_URI + "/all")
    public ResponseEntity<List<CommunicationType>> getAllCommunicationType() {
        List<CommunicationType> communicationTypeList = this.communicationTypeService.getAllCommunicationType();
        if (!CollectionUtils.isEmpty(communicationTypeList)) {
            return status(HttpStatus.OK).body(communicationTypeList);
        } else {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<CommunicationType>());
        }
    }

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONTYPE_URI + "/get/byId/{id}")
    public ResponseEntity<CommunicationType> getCommunicationTypeById(@PathVariable Long id) {
        try {
            return status(HttpStatus.OK).body(this.communicationTypeService.getCommunicationTypeById(id));
        } catch (DataValueNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body(new CommunicationType());
        }
    }

    @PostMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONTYPE_URI + "/add")
    public ResponseEntity<String> addCommunicationType(@RequestBody String newCommunicationTypeJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunicationType newCommunicationType;
        int resultCode;
        try {
            newCommunicationType = objectMapper.readValue(newCommunicationTypeJson, CommunicationType.class);
            if (this.communicationTypeService.save(newCommunicationType)) {
                resultCode = 0;
            } else {
                resultCode = 2;
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            resultCode = 4;
        }

        switch (resultCode) {
            case 0:
                return ResponseEntity.ok("OK. CommunicationType was added successfully.");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. CommunicationType could not be saved!");
            case 4:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERROR. The JSON object string could not be processed.");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. CommunicationType could not be saved!");
        }
    }

    @PutMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONTYPE_URI + "/update/{id}")
    public ResponseEntity<String> updateCommunicationType(@PathVariable Long id, @RequestBody String updateCommunicationTypeJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunicationType updateCommunicationType;
        int resultCode;
        try {
            updateCommunicationType = objectMapper.readValue(updateCommunicationTypeJson, CommunicationType.class);
            if (this.communicationTypeService.update(updateCommunicationType)) {
                resultCode = 0;
            } else {
                resultCode = 2;
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            resultCode = 4;
        }

        switch (resultCode) {
            case 0:
                return ResponseEntity.ok("OK. CommunicationType was updated successfully.");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. CommunicationType could not be saved!");
            case 4:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERROR. The JSON object string could not be processed.");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. CommunicationType could not be saved!");
        }

    }

    @DeleteMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONTYPE_URI + "/delete/{id}")
    public ResponseEntity<String> deleteCommunicationType(@PathVariable Long customerId) {
        if (this.communicationTypeService.deleteById(customerId)) {
            return ResponseEntity.ok("Your CommunicationType was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. CommunicationType does not exists!");
        }
    }

}
