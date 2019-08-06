package nl.indox.webshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
