package nl.indox.webshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.indox.webshop.dao.InventoryDao;
import nl.indox.webshop.dao.ProductDao;
import nl.indox.webshop.dto.Order;
import nl.indox.webshop.dto.Product;
import nl.indox.webshop.dto.ProductImage;
import nl.indox.webshop.repo.ProductRepo;

@Controller
public class ProductController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductRepo productRepo;
	
	
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String getAllProducts(Model model) {
		
		List<Product> allProducts = new ArrayList<>();
		for (Product product:inventoryDao.getAllProducts()) {
			productDao.setProductImages(product);
			allProducts.add(product);
			
		}
		
		logger.debug("products fetched : " + allProducts.size());
		model.addAttribute("allProducts", allProducts);
		
		return "products";
	}
	
	@RequestMapping(value="/productDetails", method=RequestMethod.GET)
	public String getProductDetails(Model model, @RequestParam(value="prodId", required=true) int prodId,
												@RequestParam(value="error", required=false, defaultValue="0") int error) {
			Product product = productRepo.findByProductId(prodId);
			model.addAttribute("product", product);
			if(error == 1) {
				model.addAttribute("quantityerror", "Het aantal beschikbare artikelen op dit moment is("
						+ inventoryDao.getProductInventory(product)+")");	
			}
		return "productDetails";
	}
	
	
}
