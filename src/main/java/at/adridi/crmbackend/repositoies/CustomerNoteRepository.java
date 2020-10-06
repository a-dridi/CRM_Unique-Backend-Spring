package at.adridi.crmbackend.repositoies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.adridi.crmbackend.model.CustomerNote;

@Repository
public interface CustomerNoteRepository extends JpaRepository<CustomerNote, Long> {

	Optional<CustomerNote> findByCustomerNoteId(Long customerNoteId);

}
