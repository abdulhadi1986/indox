package nl.indox.webshop.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
