package at.adridi.crmbackend.repositoies;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import at.adridi.crmbackend.model.CommunicationMessage;

@Repository
public interface CommunicationMessageRepository extends JpaRepository<CommunicationMessage, Long> {

	Optional<CommunicationMessage> findByCommunicationMessageId(Long communicationMessageId);

}
