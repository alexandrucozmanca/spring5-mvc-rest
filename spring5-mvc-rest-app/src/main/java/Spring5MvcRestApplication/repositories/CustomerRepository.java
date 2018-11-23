package Spring5MvcRestApplication.repositories;

import Spring5MvcRestApplication.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
