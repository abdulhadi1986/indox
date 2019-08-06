package nl.indox.webshop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.indox.webshop.dao.InventoryDao;
import nl.indox.webshop.dao.ProductDao;
import nl.indox.webshop.dto.Customer;
import nl.indox.webshop.dto.Order;
import nl.indox.webshop.dto.OrderDetail;
import nl.indox.webshop.dto.Product;
import nl.indox.webshop.dto.TestList;
import nl.indox.webshop.repo.InventoryRepo;
import nl.indox.webshop.repo.OrderDetailRepo;

@Controller
public class ShoppingcartController {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	@Autowired
	private InventoryDao inventoryDao;
	
	private Order order;// = new Order();
	private List<OrderDetail> shoppingCartItems ;//= new ArrayList<>();
	
	private void setSessionVariables(HttpSession session) {
		if(session.getAttribute("order")==null) {
			order = new Order(); 
			order.setOrderDetails(new ArrayList<OrderDetail>());
			session.setAttribute("order", order);
		}
		order = (Order) session.getAttribute("order") ;
		shoppingCartItems = order.getOrderDetails();
	}
	
	@RequestMapping(value="/shoppingcart", method=RequestMethod.GET)
	public String getShoppingCartPage(Model model, HttpSession session) {
		setSessionVariables(session);
		model.addAttribute("order", order);
		
		return "shoppingcart";
	}

	@RequestMapping(value="/addToShoppingCart" , method=RequestMethod.POST)
	public String addToShoppingCart(Model model, Product product, int quantity, HttpSession session) {
		setSessionVariables(session);
		boolean productExists = false;
		if (quantity >=0 && quantity <= inventoryDao.getProductInventory(product)) {
			OrderDetail orderItem = new OrderDetail();
			Product prod= productDao.getProductById(product.getProductId());
			orderItem.setProduct(prod);
			orderItem.setQuantity(quantity);
			
			for (int i=0 ; i <shoppingCartItems.size(); i++) {
				if (shoppingCartItems.get(i).getProduct().equals(product)) {
					shoppingCartItems.get(i).setQuantity(shoppingCartItems.get(i).getQuantity()+quantity);
					productExists=true;
					break;
				}
				
			}
			if(shoppingCartItems.size()==0 || !productExists)
				shoppingCartItems.add(orderItem);
			
			order.setOrderDetails(shoppingCartItems);
			session.setAttribute("order", order);
			return "redirect:shoppingcart";
		}
		
		return "redirect:productDetails?prodId="+product.getProductId()+"&error=1";
	}
	
	@RequestMapping(value="/delorderitem", method=RequestMethod.GET)
	public String deleteOrderItem(@RequestParam (value="prodId", required=true, defaultValue="0")int prodId, Model model) {
		
		Product prod= productDao.getProductById(prodId);
		//OrderDetail orderItem = orderDetailRepo.findById(prodId);
		for (int i=0; i<shoppingCartItems.size(); i++) {
			if (shoppingCartItems.get(i).getProduct().equals(prod)) {
				shoppingCartItems.remove(shoppingCartItems.get(i));
				i--;
			}
		}
		
		
		return "redirect:shoppingcart";
		
	}
	
	@RequestMapping(value="/addProductsToOrder", method=RequestMethod.POST)
	public String addProductsToOrder(Order order) {
		this.order = order; 
		//System.out.println("list found "+ shoppingCartItems.size());	
		return"redirect:checkout";
	}
	
	@RequestMapping (value="/checkout")
	public String checkout(Model model) {
		Customer customer = new Customer();
		order.setCustomer(customer);
		model.addAttribute("order", order);
		
		return"checkout";
	}
	
	@RequestMapping(value="/doordercheckout" , method=RequestMethod.POST)
	public String doCheckout(Order order) {
		Order checkedOrder = new Order();
		checkedOrder = order;
		return "success";
		
	}
	
	
	  @RequestMapping(value="/testlist", method=RequestMethod.GET) public String
	  testList(Model model) { TestList testList = new TestList();
	  
	  List<Integer> list = new ArrayList<>(); list.add(1); list.add(2);
	  list.add(3); testList.setListOfInt(list);
	  
	  testList.setTestId(1);
	  
	  model.addAttribute("list", testList); return "testlist"; }
	 
	
	
	  @RequestMapping(value="/testlistpost" , method=RequestMethod.POST) public
	  String testListPost(@ModelAttribute("list") TestList list, @RequestParam
	  (value="num", required=false, defaultValue="0") int num) { List<Integer>
	  checkedList = list.getListOfInt(); return "test"; }
	 
	
	
	
}
