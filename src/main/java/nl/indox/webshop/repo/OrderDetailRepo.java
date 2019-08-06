package nl.indox.webshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
	public OrderDetail findById(int Id);

}
