/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.adridi.crmbackend.controller;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.Customer;
import at.adridi.crmbackend.model.CustomerNote;
import at.adridi.crmbackend.service.CustomerNoteService;
import at.adridi.crmbackend.service.CustomerService;
import at.adridi.crmbackend.util.ApiUrls;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * REST API end point for managing CustomerNote objects.
 *
 * API: /api/customernote/
 *
 * @author A.Dridi
 *
 */
@RestController
@NoArgsConstructor
public class CustomerNoteController {

    @Autowired
    private CustomerNoteService customerNoteService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMERNOTE_URI + "/get/byCustomerId/{id}")
    public ResponseEntity<List<CustomerNote>> getCustomerNoteByCustomerId(@PathVariable Long customerId) {

        try {
            Customer customer = this.customerService.getCustomerById(customerId);
            if (!CollectionUtils.isEmpty(customer.getCustomerNoteList())) {
                return status(HttpStatus.OK).body(customer.getCustomerNoteList());
            } else {
                return status(HttpStatus.BAD_REQUEST).body(new ArrayList<CustomerNote>());
            }
        } catch (DataValueNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<CustomerNote>());
        }
    }
}
