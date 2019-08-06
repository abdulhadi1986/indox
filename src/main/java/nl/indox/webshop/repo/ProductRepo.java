package nl.indox.webshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import nl.indox.webshop.dto.Product;
@Component
public interface ProductRepo extends JpaRepository<Product, Integer> {
	Product findByProductId(int id);

}
