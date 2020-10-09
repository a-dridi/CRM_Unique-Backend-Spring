/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.crmbackend.controller;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.CommunicationMessage;
import at.adridi.crmbackend.model.Customer;
import at.adridi.crmbackend.model.CustomerNote;
import at.adridi.crmbackend.service.CommunicationMessageService;
import at.adridi.crmbackend.service.CustomerService;
import at.adridi.crmbackend.util.ApiUrls;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * REST API end point for managing CommunicationMessage objects.
 *
 * API: /api/communicationmessage/
 *
 * @author A.Dridi
 *
 */
public class CommunicationMessageController {

    @Autowired
    private CommunicationMessageService communicationMessageService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.COMMUNICATIONMESSAGE_URI + "/get/byCustomerId/{id}")
    public ResponseEntity<List<CommunicationMessage>> getCustomerNoteByCustomerId(@PathVariable Long customerId) {
        try {
            Customer customer = this.customerService.getCustomerById(customerId);
            if (!CollectionUtils.isEmpty(customer.getCommunicationMessageList())) {
                return status(HttpStatus.OK).body(customer.getCommunicationMessageList());
            } else {
                return status(HttpStatus.BAD_REQUEST).body(new ArrayList<CommunicationMessage>());
            }
        } catch (DataValueNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<CommunicationMessage>());
        }
    }

}
