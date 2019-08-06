package nl.indox.webshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.Inventory;
import nl.indox.webshop.dto.Product;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
	public Inventory findByProduct(Product product);

}
