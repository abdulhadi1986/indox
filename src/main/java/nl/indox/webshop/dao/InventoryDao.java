package nl.indox.webshop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.indox.webshop.dto.Product;
import nl.indox.webshop.repo.InventoryRepo;

@Service
public class InventoryDao {
	@Autowired
	private InventoryRepo inventoryRepo;
	
	public List<Product> getAllProducts(){
		List<Product> allProducts=new ArrayList<>();
		inventoryRepo.findAll().stream().filter(p->p.getStockQuantity()>0).forEach(inv->allProducts.add(inv.getProduct()));
		//inventoryRepo.findAll().forEach(inv->allProducts.add(inv.getProduct()));
		return allProducts;
				
	}
	
	public int getProductInventory(Product prod) {
		return inventoryRepo.findByProduct(prod).getStockQuantity();
	}
}
