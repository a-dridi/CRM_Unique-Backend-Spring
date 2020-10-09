package at.adridi.crmbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.Customer;
import at.adridi.crmbackend.service.CustomerService;
import at.adridi.crmbackend.util.ApiUrls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NoArgsConstructor;
import static org.springframework.http.ResponseEntity.status;

/**
 *
 * REST API end point for managing Customers and their CustomerNote objects and
 * CommunicationMessage objects.
 *
 * API: /api/customer/
 *
 * @author A.Dridi
 *
 */
@RestController
@NoArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customerList = this.customerService.getAllCustomer();
        if (!CollectionUtils.isEmpty(customerList)) {
            return status(HttpStatus.OK).body(customerList);
        } else {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<Customer>());
        }
    }

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/get/byId/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            return status(HttpStatus.OK).body(this.customerService.getCustomerById(id));
        } catch (DataValueNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body(new Customer());
        }
    }

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/get/byEmail/{email}")
    public ResponseEntity<Customer> getOwnerByEmail(@PathVariable String email) {
        try {
            return status(HttpStatus.OK).body(this.customerService.getCustomerByEmail(email));
        } catch (DataValueNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body(new Customer());
        }
    }

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/get/byForename/{forename}")
    public ResponseEntity<List<Customer>> getAllCustomerByForename(@PathVariable String forename) {
        List<Customer> customerList = this.customerService.getCustomerByForename(forename);
        if (!CollectionUtils.isEmpty(customerList)) {
            return status(HttpStatus.OK).body(customerList);
        } else {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<Customer>());
        }
    }

    @GetMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/get/byForename/{surname}")
    public ResponseEntity<List<Customer>> getAllCustomerBySurname(@PathVariable String surname) {
        List<Customer> customerList = this.customerService.getCustomerBySurname(surname);
        if (!CollectionUtils.isEmpty(customerList)) {
            return status(HttpStatus.OK).body(customerList);
        } else {
            return status(HttpStatus.BAD_REQUEST).body(new ArrayList<Customer>());
        }
    }

    @PostMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/add")
    public ResponseEntity<String> addCustomer(@RequestBody String newCustomerJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer newCustomer;
        int resultCode;
        try {
            newCustomer = objectMapper.readValue(newCustomerJson, Customer.class);
            resultCode = this.customerService.save(newCustomer);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            resultCode = 4;
        }

        switch (resultCode) {
            case 0:
                return ResponseEntity.ok("OK. Customer was added successfully.");
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Customer cannot be null!");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Customer could not be saved!");
            case 3:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERROR. Customer E-Mail already exists! Please select another e-mail.");
            case 4:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERROR. The JSON object string could not be processed.");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Customer could not be saved!");
        }
    }

    @PutMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/update/{id}")
    public ResponseEntity<String> updateOwner(@PathVariable Long id, @RequestBody Customer customer) {
        int resultCode = this.customerService.update(customer);

        switch (resultCode) {
            case 0:
                return ResponseEntity.ok("OK. Update was successful.");
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Customer cannot be null!");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Customer does not exist!");
            case 3:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERROR. Customer E-Mail already exists! Please select another e-mail.");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Update failed!");
        }

    }

    @DeleteMapping(ApiUrls.BASE_API_URI + ApiUrls.CUSTOMER_URI + "/delete/{id}")
    public ResponseEntity<String> deleteAccountType(@PathVariable Long customerId) {
        if (this.customerService.deleteById(customerId)) {
            return ResponseEntity.ok("Your customer was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Customer does not exists!");
        }
    }
}
