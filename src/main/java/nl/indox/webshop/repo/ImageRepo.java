package nl.indox.webshop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.indox.webshop.dto.Product;
import nl.indox.webshop.dto.ProductImage;

public interface ImageRepo extends JpaRepository<ProductImage, Integer> {
	public List<ProductImage> findByProduct(Product product);
}
