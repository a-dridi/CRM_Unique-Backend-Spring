package at.adridi.crmbackend.repositoies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.adridi.crmbackend.model.CommunicationMessage;
import at.adridi.crmbackend.model.CommunicationType;

@Repository
public interface CommunicationTypeRepository extends JpaRepository<CommunicationType, Long> {

	Optional<CommunicationType> findByCommunicationTypeId(Long communicationTypeId);

}
