package at.adridi.crmbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.Customer;
import at.adridi.crmbackend.repositoies.CustomerRepository;
import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
/**
 * All implemented DAO for Customer.
 * 
 * @author A.Dridi
 *
 */
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Save new customer.
	 * 
	 * @param newCustomer
	 * @return 0 if successful. 1: Passed customer is null. 2: Saving failed. 3:
	 *         E-Mail already exists
	 */
	public Integer save(Customer newCustomer) {
		if (newCustomer == null) {
			return 1;
		}
		
		try {
			this.getCustomerByEmail(newCustomer.getEmail());
			return 3;
		} catch (DataValueNotFoundException e) {
			// OK. E-mail can be added to new customer
		}

		if (this.customerRepository.save(newCustomer) != null) {
			return 0;
		} else {
			return 2;
		}
	}

	/**
	 * Get certain customer with the passed id. Throws DataValueNotFoundException if
	 * customer is not available.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Customer getCustomerById(long id) {
		return this.customerRepository.findByCustomerId(id)
				.orElseThrow(() -> new DataValueNotFoundException("Customer Does Not Exist"));
	}

	/**
	 * Get certain customer with the passed email. Throws DataValueNotFoundException
	 * if customer is not available.
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	public Customer getCustomerByEmail(String email) {
		return this.customerRepository.findByEmail(email)
				.orElseThrow(() -> new DataValueNotFoundException("Customer Does Not Exist"));
	}

	/**
	 * Get customer list with the passed forename. Throws DataValueNotFoundException
	 * if customers are not available.
	 * 
	 * @param forename
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Customer> getCustomerByForename(String forename) {
		return this.customerRepository.findByForename(forename)
				.orElseThrow(() -> new DataValueNotFoundException("Customer/s Do(es) Not Exist"));
	}
	
	/**
	 * Get customer list with the passed surname. Throws DataValueNotFoundException
	 * if customers are not available.
	 * 
	 * @param surname
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Customer> getCustomerBySurname(String surname) {
		return this.customerRepository.findByForename(surname)
				.orElseThrow(() -> new DataValueNotFoundException("Customer/s Do(es) Not Exist"));
	}
	
	/**
	 * Get a List of all saved customer.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Customer> getAllCustomer() {
		return this.customerRepository.findAll();
	}

	/**
	 * Update customer with the values passed in the object updateCustomer.
	 * 
	 * @param updatedCustomer
	 * @return 0: OK. 1: Passed value is null. 2: Passed customer does not exist, 3:
	 *         E-Mail already exists and belongs to another customer. 4: Update
	 *         failed.
	 */
	public int update(Customer updatedCustomer) {
		if (updatedCustomer == null) {
			return 0;
		}
		Customer customer = this.getCustomerById(updatedCustomer.getCustomerId());
		if (customer != null) {

			// Update e-mail only if e-mail does not belong to a customer already
			if (!customer.getEmail().equals(updatedCustomer.getEmail())) {
				try {
					this.getCustomerByEmail(updatedCustomer.getEmail());
					return 3;
				} catch (DataValueNotFoundException e) {
					// OK. E-mail can be updated
				}
			}

			customer.setCompanyName(updatedCustomer.getCompanyName());
			customer.setForename(updatedCustomer.getForename());
			customer.setSurname(updatedCustomer.getSurname());
			customer.setEmail(updatedCustomer.getEmail());
			customer.setTelephone(updatedCustomer.getTelephone());
			customer.setStreet(updatedCustomer.getStreet());
			customer.setCity(updatedCustomer.getCity());
			customer.setPostCode(updatedCustomer.getPostCode());
			customer.setCountry(updatedCustomer.getCountry());
			customer.setIBAN(updatedCustomer.getIBAN());
			customer.setBIC(updatedCustomer.getBIC());
			customer.setBankInformation(updatedCustomer.getBankInformation());
			customer.setWebsite(updatedCustomer.getWebsite());
			customer.setFacebookUrl(updatedCustomer.getFacebookUrl());
			customer.setTwitterUrl(updatedCustomer.getTwitterUrl());
			customer.setLinkedinUrl(updatedCustomer.getLinkedinUrl());
			customer.setXingUrl(updatedCustomer.getXingUrl());
			customer.setSocialmediaUrl(updatedCustomer.getSocialmediaUrl());
			customer.setLanguage(updatedCustomer.getLanguage());
			customer.setTimezone(updatedCustomer.getTimezone());
			customer.setNote(updatedCustomer.getNote());
			customer.setCommunicationMessageList(updatedCustomer.getCommunicationMessageList());
			customer.setCustomerNoteList(updatedCustomer.getCustomerNoteList());

			if (this.customerRepository.save(customer) != null) {
				return 0;
			} else {
				return 4;
			}
		} else {
			return 2;
		}
	}

	/**
	 * Delete an existing customer
	 * 
	 * @param customerId
	 * @return true if successful
	 */
	public boolean deleteById(Long customerId) {
		if (customerId == null || customerId == 0) {
			return false;
		}
		Customer customer = this.getCustomerById(customerId);
		if (customer != null) {
			this.customerRepository.delete(customer);
			if (this.getCustomerById(customer.getCustomerId()) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
