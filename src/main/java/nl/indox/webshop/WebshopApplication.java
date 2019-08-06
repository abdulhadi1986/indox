package nl.indox.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@ComponentScan("nl.indox.webshop.*")
public class WebshopApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
	/*
	 * @Override public void run(String... args) throws Exception { Product product
	 * = new Product(); product.setProductName("Test Product");
	 * product.setProductColor("Red"); product.setProductPrice(15);
	 * product.setProductUnit("piece"); productRepo.save(product);
	 * System.out.println("the product id is : "+
	 * productRepo.findAll().get(0).getProductId());
	 * 
	 * }
	 */
}
