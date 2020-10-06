package at.adridi.crmbackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.adridi.crmbackend.exceptions.DataValueNotFoundException;
import at.adridi.crmbackend.model.CommunicationMessage;
import at.adridi.crmbackend.repositoies.CommunicationMessageRepository;
import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
/**
 * All implemented DAO for CommunicationMessage. 
 * 
 * @author A.Dridi
 *
 */
public class CommunicationMessageService {

	@Autowired
	private CommunicationMessageRepository communicationMessageRepository;

	/**
	 * Save new CommunicationMesage.
	 * 
	 * @param newCommunicationMessage
	 * @return true if successful
	 */
	public boolean save(CommunicationMessage newCommunicationMessage) {
		if (newCommunicationMessage == null) {
			return false;
		}
		if (this.communicationMessageRepository.save(newCommunicationMessage) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get certain communicationmessage with the passed id. Throws
	 * DataValueNotFoundException if communicationmessage is not available.
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public CommunicationMessage getCommunicationMessageById(long id) {
		return this.communicationMessageRepository.findByCommunicationMessageId(id)
				.orElseThrow(() -> new DataValueNotFoundException("CommunicationMessage Does Not Exist"));
	}

	/**
	 * Get a List of all communication message
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CommunicationMessage> getAllCommunicationMessage() {
		return this.communicationMessageRepository.findAll();
	}

	/**
	 * Update CommunicationMessage with the values passed in the object updatedCommunicationMessage
	 * 
	 * @param updatedCommunicationMessage
	 * @return true if successful
	 */
	public boolean update(CommunicationMessage updatedCommunicationMessage) {
		if (updatedCommunicationMessage == null) {
			return false;
		}
		CommunicationMessage communicationMessage = this
				.getCommunicationMessageById(updatedCommunicationMessage.getCommunicationMessageId());
		if (communicationMessage != null) {
			communicationMessage.setMessage(updatedCommunicationMessage.getMessage());
			communicationMessage.setTag1(updatedCommunicationMessage.getTag1());
			communicationMessage.setTag2(updatedCommunicationMessage.getTag1());
			communicationMessage.setTag3(updatedCommunicationMessage.getTag1());
			communicationMessage.setTag4(updatedCommunicationMessage.getTag4());
			communicationMessage.setType(updatedCommunicationMessage.getType());
			communicationMessage.setUpdatedDate(new Date());
			if (this.communicationMessageRepository.save(communicationMessage) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Delete an existing communication message
	 * 
	 * @param communicationMessageId
	 * @return true if successful
	 */
	public boolean deleteById(Long communicationMessageId) {
		if (communicationMessageId == null || communicationMessageId == 0) {
			return false;
		}
		CommunicationMessage communicationMessage = this.getCommunicationMessageById(communicationMessageId);
		if (communicationMessage != null) {
			this.communicationMessageRepository.delete(communicationMessage);
			if (this.getCommunicationMessageById(communicationMessage.getCommunicationMessageId()) != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
