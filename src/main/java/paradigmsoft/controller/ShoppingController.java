package paradigmsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import paradigmsoft.model.Cart;
import paradigmsoft.model.Checkout;
import paradigmsoft.model.Products;
import paradigmsoft.model.User;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import paradigmsoft.repository.CartRepository;
import paradigmsoft.repository.CheckoutRepository;
import paradigmsoft.repository.ProductsRepository;
import paradigmsoft.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ShoppingController {

	@Autowired
	private UserRepository userRep;
	@Autowired
	private ProductsRepository productRep;
	@Autowired
	private CheckoutRepository checkoutRep;
	@Autowired
	private CartRepository cartRep;
	
	@RequestMapping(method = RequestMethod.OPTIONS)
	ResponseEntity<?> options() {
		return ResponseEntity.ok().allow(
			   HttpMethod.GET,
			   HttpMethod.POST,
			   HttpMethod.HEAD,
			   HttpMethod.OPTIONS,
			   HttpMethod.PUT,
			   HttpMethod.DELETE).build();
		
	}
	
	@GetMapping("/listofusers/")
	public ResponseEntity<List<User>> userAdded() {
		return ResponseEntity.ok(userRep.userList());
	}
	
	@GetMapping("/listofproducts/")
	public ResponseEntity<List<Products>> listProducts() {
		return ResponseEntity.ok(productRep.productList());
	}
	
	@GetMapping("/cartlist/")
	public ResponseEntity<List<Cart>> cartList(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(cartRep.getList(id));
	}
	
	@GetMapping("/productdetail/")
	public ResponseEntity<Products> productDetail(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(productRep.getProduct(id));
	}

	@GetMapping("/showuser/")
	public ResponseEntity<User> getTheUser(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(userRep.getUser(id));
	}
	
	@GetMapping("/email/")
	public ResponseEntity<String> emailUser(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(checkoutRep.getEmail(id));
	}
	
	@GetMapping("/total/")
	public ResponseEntity<Float> total(@PathVariable(value="id") int id) {
		return ResponseEntity.ok(cartRep.getTotal(id));
	}
	
	@PostMapping("/createcheckout/")
	public void checkout(@RequestBody Checkout checkout) {
		checkoutRep.createCheckout(checkout);
	}
	
	@PostMapping("/createuser/")
	public void createNewUser(@RequestBody User user) {
		userRep.createUser(user);
	}
	
	@PostMapping("/createcart/")
	public void cart(@RequestBody Cart cart) {
		cartRep.createCart(cart);
	}
	
	@PostMapping("/createproduct")
	public void createNewProd(@RequestBody Products product) {
		productRep.createProduct(product);
	}
	
	@PostMapping("/loggedin/")
	public ResponseEntity<User> loggedIn(@RequestBody User user) {
		if(userRep.login(user)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/updateuser/")
	public void updateUser(@PathVariable(value="id") int id, @RequestBody User user) {
		userRep.updateUser(id, user);
	}
	
	@DeleteMapping("/deleteuser/")
	public void deleteUser(@PathVariable(value="id") int id) {
		userRep.deleteUser(id);
	}

	@DeleteMapping("/deletebycart/")
	public void deleteByCart(@PathVariable(value="id") int id) {
		cartRep.deleteCart(id);
	}
	
	@DeleteMapping("/deletebyproduct/")
	public void deleteByProduct(@PathVariable(value="id") int id) {
		cartRep.deleteProduct(id);
	}
	
	@DeleteMapping("/deletecheckout")
	public void deleteCards(@PathVariable(value="id") int id) {
		checkoutRep.deleteCheckout(id);
	}
	
	@DeleteMapping("/deletetheproduct")
	public void deleteTheProd(@PathVariable(value="id") int id) {
		productRep.deleteProduct(id);
	}
}