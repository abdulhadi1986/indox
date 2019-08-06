package nl.indox.webshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.indox.webshop.dto.Product;
import nl.indox.webshop.repo.ImageRepo;
import nl.indox.webshop.repo.ProductRepo;

@Service
public class ProductDao {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ImageRepo imageRepo;
	
	public void setProductImages(Product product){
		product.setProductImages(imageRepo.findByProduct(product));
	}
	
	public Product getProductById(int productId) {
		Product product=new Product();
		product = productRepo.findByProductId(productId);
		setProductImages(product);
		productRepo.save(product);
		return product;
		
	}

}
