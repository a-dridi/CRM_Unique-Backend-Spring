package at.adridi.crmbackend.repositoies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.adridi.crmbackend.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerId(Long customerId);

    Optional<Customer> findByEmail(String email);

    Optional<List<Customer>> findByForename(String forename);

    Optional<List<Customer>> findBySurname(String surname);

}
