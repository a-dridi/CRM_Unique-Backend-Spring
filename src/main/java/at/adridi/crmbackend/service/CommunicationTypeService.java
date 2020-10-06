package at.adridi.crmbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.CommunicationType;
import at.adridi.crmbackend.repositoies.CommunicationTypeRepository;
import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
/**
 * All implemented DAO for CommunicationType. 
 * 
 * @author A.Dridi
 *
 */
public class CommunicationTypeService {
	@Autowired
	private CommunicationTypeRepository communicationTypeRepository;

	/**
	 * Save new CommunicationType.
	 * 
	 * @param newCommunicationMessage
	 * @return true if successful
	 */
	public boolean save(CommunicationType newCommunicationType) {
		if (newCommunicationType == null) {
			return false;
		}
		if (this.communicationTypeRepository.save(newCommunicationType) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get certain CommunicationType with the passed id. Throws
	 * DataValueNotFoundException if CommunicationType is not available.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public CommunicationType getCommunicationTypeById(long id) {
		return this.communicationTypeRepository.findByCommunicationTypeId(id)
				.orElseThrow(() -> new DataValueNotFoundException("CommunicationType Does Not Exist"));
	}

	/**
	 * Get a List of all CommunicationType
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CommunicationType> getAllCommunicationType() {
		return this.communicationTypeRepository.findAll();
	}

	/**
	 * Update CommunicationType with the values passed in the object updatedCommunicationType
	 * 
	 * @param updatedCommunicationType
	 * @return true if successful
	 */
	public boolean update(CommunicationType updatedCommunicationType) {
		if (updatedCommunicationType == null) {
			return false;
		}
		CommunicationType communicationType = this
				.getCommunicationTypeById(updatedCommunicationType.getCommunicationTypeId());
		if ( communicationType != null) {
			 communicationType.setColorHex(updatedCommunicationType.getColorHex());
			 communicationType.setTitle(updatedCommunicationType.getTitle());
			if (this.communicationTypeRepository.save(communicationType) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Delete an existing communication type
	 * 
	 * @param communicationTypeId
	 * @return true if successful
	 */
	public boolean deleteById(Long communicationTypeId) {
		if (communicationTypeId == null || communicationTypeId == 0) {
			return false;
		}
		CommunicationType communicationType = this.getCommunicationTypeById(communicationTypeId);
		if (communicationType != null) {
			this.communicationTypeRepository.delete(communicationType);
			if (this.getCommunicationTypeById(communicationType.getCommunicationTypeId()) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
