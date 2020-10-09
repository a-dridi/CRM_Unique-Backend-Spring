package at.adridi.crmbackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.CustomerNote;
import at.adridi.crmbackend.repositoies.CustomerNoteRepository;
import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
/**
 * All implemented DAO for CustomerNote.
 * 
 * @author A.Dridi
 *
 */
public class CustomerNoteService {
	@Autowired
	private CustomerNoteRepository customerNoteRepository;

	/**
	 * Save new CustomerNote.
	 * 
	 * @param newCustomerNote
	 * @return true if successful
	 */
	public boolean save(CustomerNote newCustomerNote) {
		if (newCustomerNote == null) {
			return false;
		}
		if (this.customerNoteRepository.save(newCustomerNote) != null) {
			return true;
		} else {
			return false;
		}
                
	}

	/**
	 * Get certain CustomerNote with the passed id. Throws DataValueNotFoundException if
	 * CustomerNote is not available.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public CustomerNote getCustomerNoteById(long id) {
		return this.customerNoteRepository.findByCustomerNoteId(id)
				.orElseThrow(() -> new DataValueNotFoundException("CustomerNote Does Not Exist"));
	}

	/**
	 * Get a List of all saved CustomerNote.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CustomerNote> getAllCustomerNote() {
		return this.customerNoteRepository.findAll();
	}

	/**
	 * Update customer with the values passed in the object updateCustomer.
	 * 
	 * @param updatedCustomerNote
	 * @return true if successful
	 */
	public boolean update(CustomerNote updatedCustomerNote) {
		if (updatedCustomerNote == null) {
			return false;
		}
		CustomerNote customerNote = this.getCustomerNoteById(updatedCustomerNote.getCustomerNoteId());
		if (customerNote != null) {
			customerNote.setUpdatedDate(new Date());
			customerNote.setDescription(updatedCustomerNote.getDescription());
			customerNote.setLink(updatedCustomerNote.getLink());
			customerNote.setTitle(updatedCustomerNote.getTitle());
			if (this.customerNoteRepository.save(customerNote) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Delete an existing CustomerNote
	 * 
	 * @param customerNoteId
	 * @return true if successful
	 */
	public boolean deleteById(Long customerNoteId) {
		if (customerNoteId == null || customerNoteId == 0) {
			return false;
		}
		CustomerNote customerNote = this.getCustomerNoteById(customerNoteId);
		if (customerNote != null) {
			this.customerNoteRepository.delete(customerNote);
			if (this.getCustomerNoteById(customerNote.getCustomerNoteId()) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
